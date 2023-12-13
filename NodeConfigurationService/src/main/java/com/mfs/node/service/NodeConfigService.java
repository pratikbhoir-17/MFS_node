package com.mfs.node.service;

import java.util.List;

import com.mfs.node.dao.dto.Login;
import com.mfs.node.dao.dto.NodeConfigRequestDto;
import com.mfs.node.dao.dto.OVARequestResponseDto;
import com.mfs.node.dao.dto.OtpDto;
import com.mfs.node.dao.dto.ResponseStatus;
import com.mfs.node.dao.dto.VerifyMfsaCodeResponseDto;

public interface NodeConfigService {
	public ResponseStatus login(Login login);

	public VerifyMfsaCodeResponseDto partnerInsertion(NodeConfigRequestDto dto);

	public ResponseStatus verifyOtp(String userName, String otp);

	public ResponseStatus updatePassword(String userName, String password);

	public VerifyMfsaCodeResponseDto verifyMfsaCode(Login login);

	public ResponseStatus genrateAuthyToken(String userName);

	public ResponseStatus sendEmail(OtpDto request);

	public List<OVARequestResponseDto> insertOVA(OVARequestResponseDto dto);

	public List<OVARequestResponseDto> getOVA();

}
