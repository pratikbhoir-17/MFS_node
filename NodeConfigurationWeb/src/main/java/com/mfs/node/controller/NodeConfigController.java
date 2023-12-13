package com.mfs.node.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mfs.node.dao.dto.Login;
import com.mfs.node.dao.dto.NodeConfigRequestDto;
import com.mfs.node.dao.dto.OVARequestResponseDto;
import com.mfs.node.dao.dto.OtpDto;
import com.mfs.node.dao.dto.ResponseStatus;
import com.mfs.node.dao.dto.VerifyMfsaCodeResponseDto;
import com.mfs.node.dao.util.ResponseCodes;
import com.mfs.node.service.NodeConfigService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NodeConfigController {
	private static final Logger LOGGER = Logger.getLogger(NodeConfigController.class);
	@Autowired
	private NodeConfigService nodeConfigService;

	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
	public ResponseStatus sendMail(@RequestBody OtpDto request) {
		LOGGER.info("Inside NodeConfigController ===> sendMail(): " + request);
		ResponseStatus response = null;
		if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
			response = new ResponseStatus();
			response.setStatusCode(ResponseCodes.ER500.getCode());
			response.setStatusMessage(ResponseCodes.ER515.getMessage());
			return response;
		} else if (request.getUserName() == null || request.getUserName().trim().isEmpty()) {
			response = new ResponseStatus();
			response.setStatusCode(ResponseCodes.ER500.getCode());
			response.setStatusMessage(ResponseCodes.ER516.getMessage());
			return response;
		}
		return nodeConfigService.sendEmail(request);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody Login login) {
		LOGGER.info("Inside NodeConfigController ===> login(): " + login);
		ResponseStatus response = null;
		if (login.getUserName() == null || login.getUserName().trim().isEmpty()) {
			response = new ResponseStatus();
			response.setStatusCode(ResponseCodes.ER500.getCode());
			response.setStatusMessage(ResponseCodes.ER501.getMessage());
			return ResponseEntity.ok(response);
		}
		if (login.getPassword() == null || login.getPassword().trim().isEmpty()) {
			response = new ResponseStatus();
			response.setStatusCode(ResponseCodes.ER500.getCode());
			response.setStatusMessage(ResponseCodes.ER502.getMessage());
			return ResponseEntity.ok(response);
		}
		response = nodeConfigService.login(login);
		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = "/createNodeConfig", method = RequestMethod.POST)
	public ResponseEntity<?> createNodeConfig(@RequestBody NodeConfigRequestDto dto) {
		LOGGER.info("Inside NodeConfigController ===> createNodeConfig(): " + dto);
		ResponseStatus response = null;

		if (dto.getPartnerCode() == null || dto.getPartnerCode().trim().isEmpty()) {
			response = new ResponseStatus();
			response.setStatusCode(ResponseCodes.ER500.getCode());
			response.setStatusMessage(ResponseCodes.ER503.getMessage());
			return ResponseEntity.ok(response);
		}
		if (dto.getOperator() == null || dto.getOperator().trim().isEmpty()) {
			response = new ResponseStatus();
			response.setStatusCode(ResponseCodes.ER500.getCode());
			response.setStatusMessage(ResponseCodes.ER504.getMessage());
			return ResponseEntity.ok(response);
		}
		if (dto.getCountryCode() == null || dto.getCountryCode().trim().isEmpty()) {
			response = new ResponseStatus();
			response.setStatusCode(ResponseCodes.ER500.getCode());
			response.setStatusMessage(ResponseCodes.ER505.getMessage());
			return ResponseEntity.ok(response);
		}
		if (dto.getFilePath() == null || dto.getFilePath().trim().isEmpty()) {
			response = new ResponseStatus();
			response.setStatusCode(ResponseCodes.ER500.getCode());
			response.setStatusMessage(ResponseCodes.ER506.getMessage());
			return ResponseEntity.ok(response);
		}
		if (dto.getFileName() == null || dto.getFileName().trim().isEmpty()) {
			response = new ResponseStatus();
			response.setStatusCode(ResponseCodes.ER500.getCode());
			response.setStatusMessage(ResponseCodes.ER507.getMessage());
			return ResponseEntity.ok(response);
		}
		if (dto.getJarParams() == null || dto.getJarParams().trim().isEmpty()) {
			response = new ResponseStatus();
			response.setStatusCode(ResponseCodes.ER500.getCode());
			response.setStatusMessage(ResponseCodes.ER508.getMessage());
			return ResponseEntity.ok(response);
		}
		if (dto.getMethod() == null || dto.getMethod().trim().isEmpty()) {
			response = new ResponseStatus();
			response.setStatusCode(ResponseCodes.ER500.getCode());
			response.setStatusMessage(ResponseCodes.ER509.getMessage());
			return ResponseEntity.ok(response);
		}
		if (dto.getCurrencyCode() == null || dto.getCurrencyCode().trim().isEmpty()) {
			response = new ResponseStatus();
			response.setStatusCode(ResponseCodes.ER500.getCode());
			response.setStatusMessage(ResponseCodes.ER512.getMessage());
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.ok(nodeConfigService.partnerInsertion(dto));
	}

	@RequestMapping(value = "/verifyOtp", method = RequestMethod.POST)
	public ResponseEntity<?> verifyOtp(@RequestBody Login login) {
		LOGGER.info("Inside NodeConfigController ===> verifyOtp(): " + login);
		ResponseStatus response = null;
		if (login.getUserName() == null || login.getUserName().trim().isEmpty()) {
			response = new ResponseStatus();
			response.setStatusCode(ResponseCodes.ER500.getCode());
			response.setStatusMessage(ResponseCodes.ER501.getMessage());
			return ResponseEntity.ok(response);
		}
		if (login.getOtp() == null || login.getOtp().trim().isEmpty()) {
			response = new ResponseStatus();
			response.setStatusCode(ResponseCodes.ER500.getCode());
			response.setStatusMessage(ResponseCodes.ER510.getMessage());
			return ResponseEntity.ok(response);
		}
		response = nodeConfigService.verifyOtp(login.getUserName(), login.getOtp());
		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public ResponseEntity<?> updatePassword(@RequestBody Login login) {
		LOGGER.info("Inside NodeConfigController ===> updatePassword(): " + login);
		ResponseStatus response = null;
		if (login.getUserName() == null || login.getUserName().trim().isEmpty()) {
			response = new ResponseStatus();
			response.setStatusCode(ResponseCodes.ER500.getCode());
			response.setStatusMessage(ResponseCodes.ER501.getMessage());
			return ResponseEntity.ok(response);
		}
		if (login.getPassword() == null || login.getPassword().trim().isEmpty()) {
			response = new ResponseStatus();
			response.setStatusCode(ResponseCodes.ER500.getCode());
			response.setStatusMessage(ResponseCodes.ER502.getMessage());
			return ResponseEntity.ok(response);
		}
		response = nodeConfigService.updatePassword(login.getUserName(), login.getPassword());
		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = "/verifyMfsaCode", method = RequestMethod.POST)
	public ResponseEntity<?> verifyMfsaCode(@RequestBody Login login) {
		LOGGER.info("Inside NodeConfigController ===> verifyMfsaCode(): " + login);
		ResponseStatus response = null;
		if (login.getUserName() == null || login.getUserName().trim().isEmpty()) {
			response = new ResponseStatus();
			response.setStatusCode(ResponseCodes.ER500.getCode());
			response.setStatusMessage(ResponseCodes.ER501.getMessage());
			return ResponseEntity.ok(response);
		}
		if (login.getMfsaCode() == null || login.getMfsaCode().trim().isEmpty()) {
			response = new ResponseStatus();
			response.setStatusCode(ResponseCodes.ER500.getCode());
			response.setStatusMessage(ResponseCodes.ER511.getMessage());
			return ResponseEntity.ok(response);
		}
		VerifyMfsaCodeResponseDto verifyMfsaCodeResponseDto = nodeConfigService.verifyMfsaCode(login);
		return ResponseEntity.ok(verifyMfsaCodeResponseDto);
	}

	@RequestMapping(value = "/genrateAuthyToken", method = RequestMethod.POST)
	public ResponseEntity<?> genrateAuthyToken(@RequestBody Login login) {
		LOGGER.info("Inside NodeConfigController ===> genrateAuthyToken(): " + login);
		ResponseStatus response = null;
		if (login.getUserName() == null || login.getUserName().trim().isEmpty()) {
			response = new ResponseStatus();
			response.setStatusCode(ResponseCodes.ER500.getCode());
			response.setStatusMessage(ResponseCodes.ER501.getMessage());
			return ResponseEntity.ok(response);
		}
		response = nodeConfigService.genrateAuthyToken(login.getUserName());

		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = "/addOVA", method = RequestMethod.POST)
	public ResponseEntity<?> addOVA(@RequestBody OVARequestResponseDto dto) {
		LOGGER.info("Inside NodeConfigController ===> addOVA(): " + dto);
		ResponseStatus response = new ResponseStatus();
		response.setStatusCode(ResponseCodes.ER500.getCode());
		if (dto.getSendingPartner() == null || dto.getSendingPartner().trim().isEmpty()) {
			response.setStatusMessage(ResponseCodes.ER518.getMessage());
			return ResponseEntity.ok(response);
		} else if (dto.getReceivingPartner() == null || dto.getReceivingPartner().trim().isEmpty()) {
			response.setStatusMessage(ResponseCodes.ER519.getMessage());
			return ResponseEntity.ok(response);
		} else if (dto.getOva() == null || dto.getOva().trim().isEmpty()) {
			response.setStatusMessage(ResponseCodes.ER520.getMessage());
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.ok(nodeConfigService.insertOVA(dto));
	}

	@RequestMapping(value = "/getOVA", method = RequestMethod.GET)
	public ResponseEntity<?> getOVA() {
		LOGGER.info("Inside NodeConfigController ===> getOVA()");
		return ResponseEntity.ok(nodeConfigService.getOVA());
	}
}
