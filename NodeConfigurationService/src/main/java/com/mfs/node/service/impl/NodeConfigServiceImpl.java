package com.mfs.node.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfs.node.dao.NodeConfigDao;
import com.mfs.node.dao.PartnerConfigDao;
import com.mfs.node.dao.RemoteDbDao;
import com.mfs.node.dao.SystemConfigDao;
import com.mfs.node.dao.dto.CountryDto;
import com.mfs.node.dao.dto.CurrencyDto;
import com.mfs.node.dao.dto.JarParamDto;
import com.mfs.node.dao.dto.Login;
import com.mfs.node.dao.dto.MethodDto;
import com.mfs.node.dao.dto.NodeConfigRequestDto;
import com.mfs.node.dao.dto.OVARequestResponseDto;
import com.mfs.node.dao.dto.OtpDto;
import com.mfs.node.dao.dto.PartnerConfigResponseDto;
import com.mfs.node.dao.dto.ResponseStatus;
import com.mfs.node.dao.dto.VerifyMfsaCodeResponseDto;
import com.mfs.node.dao.model.CashPickupPartner;
import com.mfs.node.dao.model.Countries;
import com.mfs.node.dao.model.Currencies;
import com.mfs.node.dao.model.JarParameters;
import com.mfs.node.dao.model.MethodType;
import com.mfs.node.dao.model.OVA;
import com.mfs.node.dao.model.PartnerConfig;
import com.mfs.node.dao.model.PartnerCurbi;
import com.mfs.node.dao.model.User;
import com.mfs.node.dao.util.AESDecryption;
import com.mfs.node.dao.util.ResponseCodes;
import com.mfs.node.dao.util.SendEmail;
import com.mfs.node.dao.util.TwoFAAuth;
import com.mfs.node.dao.util.Util;
import com.mfs.node.service.NodeConfigService;

@Service("NodeConfigService")
public class NodeConfigServiceImpl implements NodeConfigService {

	@Autowired
	private NodeConfigDao nodeConfigDao;

	@Autowired
	private PartnerConfigDao partnerConfigDao;

	@Autowired
	private SendEmail sendEmail;

	@Autowired
	private SystemConfigDao systemConfigDao;

	@Autowired
	private RemoteDbDao remoteDbDao;

	private static final Logger LOGGER = Logger.getLogger(NodeConfigServiceImpl.class);

	public ResponseStatus login(Login login) {
		LOGGER.info("Inside NodeConfigServiceImpl ===> login()");
		ResponseStatus res = new ResponseStatus();
		try {

			User user = nodeConfigDao.login(login.getUserName());
			if (user != null) {
				Map<String, String> configMap = systemConfigDao.getConfigDetailsMap();
				if (AESDecryption.checkPassword(user.getPassword(), configMap.get("passKey"), login.getPassword())) {
					res.setStatusMessage(ResponseCodes.S200.getMessage());
					res.setStatusCode(ResponseCodes.S200.getCode());
					res.setUserName(login.getUserName());
				} else {
					res.setStatusMessage(ResponseCodes.ER513.getMessage());
					res.setStatusCode(ResponseCodes.ER500.getCode());
				}

			} else {

				res.setStatusMessage(ResponseCodes.ER501.getMessage());
				res.setStatusCode(ResponseCodes.ER500.getCode());
			}
		} catch (Exception e) {
			res.setStatusMessage(ResponseCodes.ER514.getMessage());
			res.setStatusCode(ResponseCodes.ER500.getCode());
			LOGGER.error("==>Exception in class: NodeConfigServiceImpl method: login ", e);

		}
		return res;
	}

	public VerifyMfsaCodeResponseDto partnerInsertion(NodeConfigRequestDto nodeConfigRequestDto) {
		LOGGER.info("Inside NodeConfigServiceImpl ===> partnerInsertion()");
		ResponseStatus res = new ResponseStatus();
		VerifyMfsaCodeResponseDto responseDto = new VerifyMfsaCodeResponseDto();
		PartnerCurbi dto = new PartnerCurbi();
		try {
			PartnerConfig partnerConfig = nodeConfigDao.nodeConfigByPartnerCode(nodeConfigRequestDto.getPartnerCode(),
					nodeConfigRequestDto.getMethod());

			if (partnerConfig == null) {
				partnerConfigSave(null, nodeConfigRequestDto);

			} else {
				partnerConfigSave(partnerConfig, nodeConfigRequestDto);

			}
			// checking method for cashpickup and partner_curbi
			if (nodeConfigRequestDto.getMethod().equalsIgnoreCase("transfer")) {

				if (nodeConfigRequestDto.getReference() != null
						&& !nodeConfigRequestDto.getReference().trim().isEmpty()) {

					CashPickupPartner cp = nodeConfigDao.getCashPickupPartner(nodeConfigRequestDto.getPartnerCode());
					if (cp != null) {
						cp.setReference(nodeConfigRequestDto.getReference());
						nodeConfigDao.update(cp);
					} else {
						CashPickupPartner cashPickupPartner = new CashPickupPartner();
						cashPickupPartner.setReference(nodeConfigRequestDto.getReference());
						cashPickupPartner.setSpCode(nodeConfigRequestDto.getPartnerCode());
						nodeConfigDao.save(cashPickupPartner);
					}

				}

				// partner_curbi insert update based on existence
				dto.setCorporate_code(nodeConfigRequestDto.getPartnerCode());
				dto.setCurbi_trxnid(nodeConfigRequestDto.getCurbiCode());
				dto.setCurrency_code(nodeConfigRequestDto.getCurrencyCode());
				dto.setCountry_code(nodeConfigRequestDto.getCountryCode());

				int isPartnerCur = partnerConfigDao.getPartnerCurbi(nodeConfigRequestDto.getPartnerCode());
				if (isPartnerCur == 0) {
					partnerConfigDao.addPartnerCurbi(dto);
				} else {
					partnerConfigDao.updatePartnerCurbi(dto);
				}

			}

			// save data on remote server for 3
			// tables-partner_curbi,cashpickup_partner,partner_configs
			int isPartnerconfig = remoteDbDao.getPartnerConfig(nodeConfigRequestDto.getPartnerCode(),
					nodeConfigRequestDto.getMethod());
			if (isPartnerconfig == 0) {
				remoteDbDao.addPartnerConfig(nodeConfigRequestDto);
			} else {
				remoteDbDao.updatePartnerConfig(nodeConfigRequestDto);
			}
			if (nodeConfigRequestDto.getMethod().equalsIgnoreCase("transfer")) {
				if (nodeConfigRequestDto.getReference() != null
						&& !nodeConfigRequestDto.getReference().trim().isEmpty()) {
					int isCashPickup = remoteDbDao.getCashPickupPartner(nodeConfigRequestDto.getPartnerCode());
					if (isCashPickup == 0) {
						remoteDbDao.addCashPickupPartner(nodeConfigRequestDto.getPartnerCode(),
								nodeConfigRequestDto.getReference());
					} else {
						remoteDbDao.updateCashPickupPartner(nodeConfigRequestDto.getPartnerCode(),
								nodeConfigRequestDto.getReference());
					}
				}

				int isPartnerCurbi = remoteDbDao.getPartnerCurbi(nodeConfigRequestDto.getPartnerCode());
				if (isPartnerCurbi == 0) {
					remoteDbDao.addPartnerCurbi(dto);
				} else {
					remoteDbDao.updatePartnerCurbi(dto);
				}
			}

		} catch (Exception e) {

			LOGGER.error("==>Exception in class: NodeConfigServiceImpl method: partnerInsertion ", e);

		}

		try {

			responseDto = prepareResponse();

		} catch (Exception e) {
			LOGGER.error("==>Exception in class: NodeConfigServiceImpl method: partnerInsertion ", e);
			res.setStatusCode(ResponseCodes.ER500.getCode());
			res.setStatusMessage(ResponseCodes.ER514.getMessage());
			responseDto.setStatus(res);
		}

		return responseDto;
	}

	public ResponseStatus verifyOtp(String userName, String otp) {
		LOGGER.info("Inside NodeConfigServiceImpl ===> verifyOtp()");
		ResponseStatus res = new ResponseStatus();
		try {
			User user = nodeConfigDao.verifyOtp(otp, userName);
			if (user != null) {

				res.setStatusMessage(ResponseCodes.S200.getMessage());
				res.setStatusCode(ResponseCodes.S200.getCode());
				res.setUserName(userName);
			} else {

				res.setStatusCode(ResponseCodes.ER500.getCode());
				res.setStatusMessage(ResponseCodes.ER510.getMessage());

			}
		} catch (Exception e) {

			res.setStatusMessage(ResponseCodes.ER514.getMessage());
			res.setStatusCode(ResponseCodes.ER500.getCode());
			LOGGER.error("==>Exception in class: NodeConfigServiceImpl method: verifyOtp ", e);

		}
		return res;

	}

	public ResponseStatus updatePassword(String userName, String password) {
		LOGGER.info("Inside NodeConfigServiceImpl ===> updatePassword()");
		ResponseStatus res = new ResponseStatus();
		try {
			User user = nodeConfigDao.getUserByUserName(userName);
			if (user != null) {
				Map<String, String> configMap = systemConfigDao.getConfigDetailsMap();
				String pass = AESDecryption.encryption(password, configMap.get("passKey"));
				user.setPassword(pass);
				user.setDate(new Date());
				nodeConfigDao.update(user);

				res.setStatusMessage(ResponseCodes.S200.getMessage());
				res.setStatusCode(ResponseCodes.S200.getCode());

			} else {

				res.setStatusCode(ResponseCodes.ER500.getCode());
				res.setStatusMessage(ResponseCodes.ER501.getMessage());

			}
		} catch (Exception e) {
			res.setStatusCode(ResponseCodes.ER500.getCode());
			res.setStatusMessage(ResponseCodes.ER514.getMessage());
			LOGGER.error("==>Exception in class: NodeConfigServiceImpl method: updatePassword ", e);

		}
		return res;

	}

	public VerifyMfsaCodeResponseDto verifyMfsaCode(Login login) {
		LOGGER.info("Inside NodeConfigServiceImpl ===> verifyMfsaCode()");
		VerifyMfsaCodeResponseDto responseDto = new VerifyMfsaCodeResponseDto();
		ResponseStatus status = new ResponseStatus();
		try {

			User user = nodeConfigDao.getUserByUserName(login.getUserName());

			if (user != null) {

				if (!TwoFAAuth.checkMfsaCode(user.getSecureKey(), login.getMfsaCode())) {
					status.setStatusCode(ResponseCodes.ER500.getCode());
					status.setStatusMessage(ResponseCodes.ER511.getMessage());
					responseDto.setStatus(status);
				} else {

					List<PartnerConfig> list = partnerConfigDao.getDetails();
					PartnerConfigResponseDto dto = null;
					List<PartnerConfigResponseDto> dtoList = new ArrayList<PartnerConfigResponseDto>();
					if (!list.isEmpty()) {
						for (PartnerConfig cfg : list) {
							dto = new PartnerConfigResponseDto();
							dto.setPartnerCode(cfg.getPartnerCode());
							dto.setName(cfg.getName());
							dto.setCountryCode(cfg.getCountryCode());
							dto.setCurrencyCode(cfg.getCurrencyCode());
							dto.setOperator(cfg.getOperator());
							dto.setMinPerTxLimit(cfg.getMinPerTxLimit());
							dto.setMaxPerTxLimit(cfg.getMaxPerTxLimit());
							dto.setFileName(cfg.getJarFileName());
							dto.setFilePath(cfg.getJarFilePath());
							dto.setJarParams(cfg.getJarParams());
							dto.setMethod(cfg.getMethod());
							dto.setPrefix(cfg.getPrefix());
							dto.setCurbiCode(cfg.getCurbiCode());
							dto.setReference(cfg.getReference());
							dtoList.add(dto);
						}
						responseDto.setPartnerList(dtoList);

					}
					List<Countries> countriesList = partnerConfigDao.getCountries();
					List<CountryDto> coList = new ArrayList<CountryDto>();
					CountryDto coDto = null;
					for (Countries country : countriesList) {
						coDto = new CountryDto();
						coDto.setCountryCode(country.getCountryCode());
						coList.add(coDto);
					}
					responseDto.setCountryList(coList);

					List<Currencies> currenciesList = partnerConfigDao.getCurrencies();
					CurrencyDto cuDto = null;
					List<CurrencyDto> cuList = new ArrayList<CurrencyDto>();
					for (Currencies currency : currenciesList) {
						cuDto = new CurrencyDto();
						cuDto.setCurrencySymbol(currency.getSymbol());
						cuList.add(cuDto);
					}
					responseDto.setCurrencyList(cuList);

					List<MethodType> methodTypeList = partnerConfigDao.getMethodType();
					List<MethodDto> methodList = new ArrayList<MethodDto>();
					MethodDto methodDto = null;
					for (MethodType method : methodTypeList) {
						methodDto = new MethodDto();
						methodDto.setMethodName(method.getMethodName());
						methodList.add(methodDto);
					}
					responseDto.setMethodTypeList(methodList);

					List<JarParameters> jarParametersList = partnerConfigDao.getAllParametersName();
					List<JarParamDto> paramList = new ArrayList<JarParamDto>();
					JarParamDto jarDto = null;
					for (JarParameters jar : jarParametersList) {
						jarDto = new JarParamDto();
						jarDto.setParamName(jar.getJarParametersName());
						paramList.add(jarDto);
					}
					responseDto.setParamList(paramList);

					status.setStatusMessage(ResponseCodes.S200.getMessage());
					status.setStatusCode(ResponseCodes.S200.getCode());
					responseDto.setStatus(status);

				}
			} else {

				status.setStatusCode(ResponseCodes.ER500.getCode());
				status.setStatusMessage(ResponseCodes.ER511.getMessage());
				responseDto.setStatus(status);
			}

		} catch (Exception e) {

			status.setStatusCode(ResponseCodes.ER500.getCode());
			status.setStatusMessage(ResponseCodes.ER514.getMessage());
			responseDto.setStatus(status);
			LOGGER.error("==>Exception in class: NodeConfigServiceImpl method: verifyMfsaCode ", e);

		}
		return responseDto;
	}

	public ResponseStatus genrateAuthyToken(String userName) {
		LOGGER.info("Inside NodeConfigServiceImpl ===> genrateAuthyToken()");
		ResponseStatus res = new ResponseStatus();
		try {
			User user = nodeConfigDao.login(userName);
			if (user != null) {
				String secureKey = TwoFAAuth.generateSecretKey();
				user.setSecureKey(secureKey);
				// Map<String, String> configMap =
				// systemConfigDao.getConfigDetailsMap();
				// String codeData =
				// TwoFAAuth.getGoogleAuthenticatorBarCode(secureKey,
				// configMap.get("account_name"),
				// configMap.get("issuer"));

				// BitMatrix bitMatrix = new
				// MultiFormatWriter().encode(codeData,
				// BarcodeFormat.QR_CODE, 300, 300);
				// // set filepath based on system
				// try (FileOutputStream out = new
				// FileOutputStream(configMap.get("QR_Path")))
				// /// home/hp/abc/qrcode.png
				// {
				// MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
				// }
				nodeConfigDao.update(user);
				res.setSecureKey(secureKey);
				res.setStatusMessage(ResponseCodes.S200.getMessage());
				res.setStatusCode(ResponseCodes.S200.getCode());

			} else {
				res.setStatusMessage(ResponseCodes.ER501.getMessage());
				res.setStatusCode(ResponseCodes.ER500.getCode());
			}
		} catch (Exception e) {
			res.setStatusCode(ResponseCodes.ER500.getCode());
			res.setStatusMessage(ResponseCodes.ER514.getMessage());
			LOGGER.error("==>Exception in class: NodeConfigServiceImpl method: genrateAuthyToken ", e);

		}
		return res;
	}

	public ResponseStatus sendEmail(OtpDto request) {
		LOGGER.info("Inside NodeConfigServiceImpl ===> sendEmail()");
		ResponseStatus status = new ResponseStatus();
		try {
			User user = nodeConfigDao.login(request.getUserName());
			if (user == null) {
				status.setStatusCode(ResponseCodes.ER500.getCode());
				status.setStatusMessage(ResponseCodes.ER501.getMessage());
			} else {
				String otp = String.valueOf(Util.generateOTP());
				user.setOtp(otp);
				sendEmail.sendMail(request.getEmail(), otp);
				nodeConfigDao.update(user);
				status.setStatusCode(ResponseCodes.S200.getCode());
				status.setStatusMessage(ResponseCodes.S200.getMessage());
				status.setUserName(request.getUserName());
			}
		} catch (Exception e) {
			status.setStatusCode(ResponseCodes.ER500.getCode());
			status.setStatusMessage(ResponseCodes.ER514.getMessage());
			LOGGER.error("==>Exception in class: NodeConfigServiceImpl method: sendEmail ", e);

		}

		return status;
	}

	/*
	 * public String getPartnerCurbi(String partnerCode) {
	 * 
	 * PartnerCurbi p = nodeConfigDao.getPartnerCUrbi(partnerCode);
	 * System.out.println(p); return null; }
	 */

	public void partnerConfigSave(PartnerConfig partnerConfig, NodeConfigRequestDto nodeConfigRequestDto)

	{
		if (partnerConfig == null) {
			PartnerConfig partnerConfigs = new PartnerConfig();
			partnerConfigs.setCountryCode(nodeConfigRequestDto.getCountryCode());
			partnerConfigs.setCurrencyCode(nodeConfigRequestDto.getCurrencyCode());
			partnerConfigs.setJarFileName(nodeConfigRequestDto.getFileName());
			partnerConfigs.setJarFilePath(nodeConfigRequestDto.getFilePath());
			partnerConfigs.setJarParams(nodeConfigRequestDto.getJarParams());
			partnerConfigs.setMethod(nodeConfigRequestDto.getMethod());
			partnerConfigs.setOperator(nodeConfigRequestDto.getOperator());
			partnerConfigs.setPartnerCode(nodeConfigRequestDto.getPartnerCode());
			partnerConfigs.setName(nodeConfigRequestDto.getName());
			partnerConfigs.setPrefix(nodeConfigRequestDto.getPrefix());
			partnerConfigs.setCurbiCode(nodeConfigRequestDto.getCurbiCode());
			if (nodeConfigRequestDto.getReference() != null) {
				partnerConfigs.setReference(nodeConfigRequestDto.getReference());
			}
			if (nodeConfigRequestDto.getMaxPerTxLimit() > 0) {
				partnerConfigs.setMaxPerTxLimit(nodeConfigRequestDto.getMaxPerTxLimit());
			} else {
				partnerConfigs.setMaxPerTxLimit(0);
			}
			if (nodeConfigRequestDto.getMinPerTxLimit() > 0) {
				partnerConfigs.setMinPerTxLimit(nodeConfigRequestDto.getMinPerTxLimit());
			} else {
				partnerConfigs.setMaxPerTxLimit(0);
			}

			nodeConfigDao.save(partnerConfigs);

		} else {
			partnerConfig.setCountryCode(nodeConfigRequestDto.getCountryCode());
			partnerConfig.setCurrencyCode(nodeConfigRequestDto.getCurrencyCode());
			partnerConfig.setJarFileName(nodeConfigRequestDto.getFileName());
			partnerConfig.setJarFilePath(nodeConfigRequestDto.getFilePath());
			partnerConfig.setJarParams(nodeConfigRequestDto.getJarParams());
			partnerConfig.setPrefix(nodeConfigRequestDto.getPrefix());
			partnerConfig.setCurbiCode(nodeConfigRequestDto.getCurbiCode());
			partnerConfig.setMaxPerTxLimit(nodeConfigRequestDto.getMaxPerTxLimit());
			partnerConfig.setMinPerTxLimit(nodeConfigRequestDto.getMinPerTxLimit());
			if (nodeConfigRequestDto.getReference() != null) {
				partnerConfig.setReference(nodeConfigRequestDto.getReference());
			}

			partnerConfig.setMethod(nodeConfigRequestDto.getMethod());
			partnerConfig.setOperator(nodeConfigRequestDto.getOperator());
			partnerConfig.setPartnerCode(nodeConfigRequestDto.getPartnerCode());
			partnerConfig.setName(nodeConfigRequestDto.getName());
			nodeConfigDao.update(partnerConfig);

		}
	}

	public VerifyMfsaCodeResponseDto prepareResponse()

	{
		VerifyMfsaCodeResponseDto res = new VerifyMfsaCodeResponseDto();
		List<PartnerConfig> list = partnerConfigDao.getDetails();
		PartnerConfigResponseDto dto = null;
		ResponseStatus responseStatus = new ResponseStatus();
		List<PartnerConfigResponseDto> dtoList = new ArrayList<PartnerConfigResponseDto>();
		if (!list.isEmpty()) {
			for (PartnerConfig cfg : list) {
				dto = new PartnerConfigResponseDto();
				dto.setPartnerCode(cfg.getPartnerCode());
				dto.setName(cfg.getName());
				dto.setCountryCode(cfg.getCountryCode());
				dto.setCurrencyCode(cfg.getCurrencyCode());
				dto.setOperator(cfg.getOperator());
				dto.setMinPerTxLimit(cfg.getMinPerTxLimit());
				dto.setMaxPerTxLimit(cfg.getMaxPerTxLimit());
				dto.setFileName(cfg.getJarFileName());
				dto.setFilePath(cfg.getJarFilePath());
				dto.setJarParams(cfg.getJarParams());
				dto.setMethod(cfg.getMethod());
				dto.setPrefix(cfg.getPrefix());
				dto.setCurbiCode(cfg.getCurbiCode());
				dto.setReference(cfg.getReference());
				dtoList.add(dto);
			}
			res.setPartnerList(dtoList);

		}

		responseStatus.setStatusMessage(ResponseCodes.S200.getMessage());
		responseStatus.setStatusCode(ResponseCodes.S200.getCode());
		res.setStatus(responseStatus);
		return res;
	}

	@Override
	public List<OVARequestResponseDto> insertOVA(OVARequestResponseDto dto) {
		LOGGER.info("Inside NodeConfigServiceImpl ===> insertOVA()");
		List<OVARequestResponseDto> response = new ArrayList<>();
		try {
			OVA isExist = nodeConfigDao.getOVABySPAndRP(dto.getSendingPartner(), dto.getReceivingPartner());
			if (isExist != null) {
				isExist.setOva(dto.getOva());
				nodeConfigDao.update(isExist);
			} else {
				OVA model = new OVA();
				model.setOva(dto.getOva());
				model.setSPCode(dto.getSendingPartner());
				model.setRPCode(dto.getReceivingPartner());
				nodeConfigDao.save(model);
			}

			// remote db
			int count = remoteDbDao.getOVA(dto.getSendingPartner(), dto.getReceivingPartner());
			if (count != 0) {
				remoteDbDao.updateOVA(dto.getSendingPartner(), dto.getReceivingPartner(), dto.getOva());
			} else {
				remoteDbDao.addOVA(dto.getSendingPartner(), dto.getReceivingPartner(), dto.getOva());
			}
		} catch (Exception e) {
			LOGGER.error("Exception occured inside NodeConfigServiceImpl ==> insertOVA() ", e);
		}
		response = getOVA();
		return response;
	}

	@Override
	public List<OVARequestResponseDto> getOVA() {
		LOGGER.info("Inside NodeConfigServiceImpl ===> getOVA()");
		List<OVARequestResponseDto> dtoList = new ArrayList<>();
		try {
			List<OVA> modelList = nodeConfigDao.getAllOVA();
			for (OVA model : modelList) {
				OVARequestResponseDto resDto = new OVARequestResponseDto();
				resDto.setOva(model.getOva());
				resDto.setSendingPartner(model.getSPCode());
				resDto.setReceivingPartner(model.getRPCode());
				dtoList.add(resDto);
			}

		} catch (Exception e) {
			LOGGER.error("Exception occured inside NodeConfigServiceImpl ==> getOVA() ", e);
		}
		return dtoList;
	}

}