package com.wipro.fhir.controller.carecontext;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Description;

import com.wipro.fhir.service.care_context.CareContextService;
import com.wipro.fhir.utils.exception.FHIRException;
import com.wipro.fhir.utils.response.OutputResponse;

@ExtendWith(MockitoExtension.class)
class CareContextControllerTest {

	@InjectMocks
	CareContextController careContextController;

	@Mock
	CareContextService careContextService;

	@Test
	@Description("Tests successful generation of a One-Time Password (TC_GenerateOTP_Success_001)")
	void generateOTPTest() throws FHIRException {

		OutputResponse response = new OutputResponse();

		String Authorization = "Authorization";

		String request = "{\"healthID\":\"H123\",\"authenticationMode\":\"Authenticated\",\"healthIdNumber\":\"H123Elth\"}";

		String s = request;

		when(careContextService.generateOTPForCareContext(request)).thenReturn(s);

		response.setResponse(s);

		Assertions.assertNotNull(request);
		Assertions.assertEquals(response.toString(), careContextController.generateOTP(request, Authorization));
	}

	@Test
	@Description("Tests handling of FHIRE exception in CareContext service during OTP generation (TC_GenerateOTP_CareContextFHIRE_002)")
	void testGenerateOTP_CareContextServiceThrowsFHIRException() throws Exception {

		String Authorization = "Authorization";

		String request = null;

		String response = careContextController.generateOTP(request, Authorization);

		Assertions.assertNull(request);
		Assertions.assertEquals(response, careContextController.generateOTP(request, Authorization));

	}

	@Test
	@Description("Tests handling of generic exceptions during OTP generation (TC_GenerateOTP_Exception_003)")
	void testGenerateOTP_Exception() throws Exception {

		String Authorization = "Authorization";

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		when(careContextService.generateOTPForCareContext(request)).thenThrow(FHIRException.class);

		String response = careContextController.generateOTP(request, Authorization);

		Assertions.assertEquals(response, careContextController.generateOTP(request, Authorization));
	}

	@Test
	@Description("Tests successful validation of OTP and creation of CareContext (TC_ValidateOTPAndCreateCareContext_Success_001)")
	void validateOTPAndCreateCareContextTest() throws FHIRException {

		OutputResponse response = new OutputResponse();

		String request = "{\"healthID\":\"H123\",\"visitCode\":\"H123\",\"beneficiaryID\":\"H123\",\"beneficiaryRegID\":\"H123\",\"otp\":\"H123\",\"txnId\":\"H123\",\"visitcategory\":\"H123\",\"healthIdNumber\":\"Hy23654Eesrdr\"}";

		String Authorization = "Authorization";

		String s = request;

		when(careContextService.validateOTPAndCreateCareContext(request)).thenReturn(s);

		response.setResponse(s);

		Assertions.assertNotNull(request);
		Assertions.assertEquals(response.toString(),
				careContextController.validateOTPAndCreateCareContext(request, Authorization));

	}

	@Test
	@Description("Tests handling of FHIRE exception during CareContext creation (TC_ValidateOTPAndCreateCareContext_FHIRE_002)")
	void testValidateOTPAndCreateCareContextThrowsFHIRException() throws Exception {

		String Authorization = "Authorization";

		String request = null;

		String response = careContextController.validateOTPAndCreateCareContext(request, Authorization);

		Assertions.assertNull(request);
		Assertions.assertEquals(response,
				careContextController.validateOTPAndCreateCareContext(request, Authorization));

	}

	@Test
	@Description("Tests handling of generic exceptions during OTP validation and CareContext creation (TC_ValidateOTPAndCreateCareContext_Exception_003)")
	void testValidateOTPAndCreateCareContext_Exception() throws Exception {

		String Authorization = "Authorization";

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		when(careContextService.validateOTPAndCreateCareContext(request)).thenThrow(FHIRException.class);

		String response = careContextController.validateOTPAndCreateCareContext(request, Authorization);

		Assertions.assertEquals(response,
				careContextController.validateOTPAndCreateCareContext(request, Authorization));
	}

	@Test
	@Description("Tests successful saving of CareContext to MongoDB (TC_SaveCareContextToMongo_Success_001)")
	void saveCareContextToMongoTest() throws FHIRException {

		String Authorization = "Authorization";

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = careContextController.saveCareContextToMongo(request, Authorization);

		Assertions.assertEquals(response,
				careContextController.saveCareContextToMongo(request, Authorization));

	}

}
