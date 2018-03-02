package com.lj.payment.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lj.payment.utils.dataUtils.DecryptUtils;
import com.lj.payment.vo.CommBilngUnmtchVO;
import com.lj.payment.vo.EmailParamVo;
import com.lj.payment.vo.RechargeHistoryVO;
import com.lj.payment.vo.TBBilngDtlVO;
import com.lj.support.common.excel.vo.ExcelInfoVO;
import com.lj.support.common.excel.vo.ExcelInfoVO.ExcelType;
import com.lj.support.common.excel.vo.ExcelInfoVO.SequenceOrder;
import com.lj.support.common.util.BinderUtils;
import com.lj.support.common.excel.vo.ExcelTableTitleVO;
import com.lj.support.common.excel.vo.ExcelTitleVO;

@Component("BilngUtils")
public class BilngUtils {

	/*@Resource(name = "kmsMasterKeyService")
	private KmsMasterKeyService kmsMasterKeyService;*/
	
	@Resource(name = "DecryptUtils")
	private DecryptUtils decryptUtils;
	
	@Value("#{localProperty}")
	private Properties prop;
	
	/**
	 * getTodayYYYYMMDD
	 * ckim (2017. 11. 1. 오전 10:25:51)
	 * desc: 오늘일자 YYYYMMDD 가져오기
	 * @return
	 */
	public String getTodayYYYYMMDD() {
		String curDate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);		
		return curDate;
	}
	
	/**
	 * getTodayYYMMDD
	 * ckim (2017. 11. 1. 오전 10:25:51)
	 * desc: 오늘일자 YYYYMMDD 가져오기
	 * @return
	 */
	public String getTodayYYMMDD() {
		String curDate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);		
		return curDate.substring(2, 8);
	}
	
	/**
	 * getIpAddr
	 * ckim (2017. 11. 1. 오전 10:34:53)
	 * desc: IP 가져오기 
	 * @return
	 * @throws UnknownHostException 
	 */
	public String getIp() throws UnknownHostException {
		InetAddress local = InetAddress.getLocalHost();
		String ip = local.getHostAddress();
		//		System.out.println("ip: " + ip);
		return ip;
	}

	/**
	 * 
	 * ckim (2018. 2. 22. 오후 12:14:29)<br/>
	 * desc   :  DateString -> date 변환
	 * 
	 * @param yyyyMMdd
	 * @param dateFormat
	 * @return
	 * @throws ParseException
	 */
	public Date convertDataStrToDate(String yyyyMMdd, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = sdf.parse(yyyyMMdd);
		} catch (ParseException e) {
			
		}
		return date;
	}
	
	
	public double convertAmtStrToNumWithRoundOff(String amt) {

		amt	= amt.trim();
		amt = StringUtils.deleteWhitespace(amt);
		
		if(StringUtils.ordinalIndexOf(amt, ".", 1) == -1) {
			return Double.parseDouble(amt.toString());
		}
		
		String[] amtSpilted = StringUtils.split(amt, ".");
		StringBuilder builder = new StringBuilder();
		builder.append(amtSpilted[0]);
		builder.append(".");
		builder.append(StringUtils.substring(amtSpilted[1], 0, 2));
		
		return Double.parseDouble(builder.toString());
	}
	/**
	 * 
	 * ckim (2017. 12. 28. 오후 3:37:12)<br/>
	 * desc   :  Date문자열을 yymmdd형태로 변환
	 * 
	 * @param dateStr
	 * @return
	 */
	public String convertDateStrToYymmdd(String dateStr) {

		dateStr = StringUtils.deleteWhitespace(dateStr);
		dateStr = StringUtils.remove(dateStr, "-");
		dateStr = StringUtils.substring(dateStr, 2, 8);
		
		return dateStr;
	}
	/**
	 * 
	 * ckim (2017. 11. 17. 오전 7:46:45)
	 * desc: 좌측 정렬 고정 길이 만들어 주기
	 * 중복사용중 확인 후 삭제필요 
	 * @param orgStr
	 * @param moLen
	 */
	public String makeLArrStr(String orgStr, int moLen) {
		String retStr = "";
		if (orgStr.length() < moLen) {
			retStr = orgStr + StringUtils.repeat(" ", moLen - orgStr.length());
		} else {
			retStr = orgStr; 
		}
		return retStr; 
	}

	/**
	 * 
	 * ckim (2017. 11. 17. 오전 11:17:04)
	 * desc: 숫자형태의 0채워서 고정 길이  만들어주기
	 * 중복사용중 확인 후 삭제필요
	 * @param orgStr
	 * @param moLen
	 * @return
	 */
	public String makeStrForNum(String orgStr, int moLen) {
		String retStr = "";
		if (orgStr.length() < moLen) {
			retStr = StringUtils.repeat("0", moLen - orgStr.length()) + orgStr;	
		} else {
			retStr = orgStr; 
		}
		return retStr; 
	}
	
	/**
	 * 
	 * ckim (2017. 11. 17. 오후 3:23:38)
	 * desc: Date를 원하는 String으로 변환
	 * @param date
	 * @param type
	 * @return
	 */
	public String convertDateToString(Date date, String type) {
		String retStr;
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		retStr = sdf.format(date);
		return retStr;
	}
	
	/**
	 * 
	 * ckim (2018. 1. 19. 오후 7:07:38)<br/>
	 * desc   :  카드번호 디코딩
	 * 
	 * @param encodedList
	 * @return
	 */
	public List<TBBilngDtlVO> decryptCcNoList(List<TBBilngDtlVO> encodedList){
		for(TBBilngDtlVO tmpVo : encodedList) {			
			String decCcNo = decryptUtils.decrypt(tmpVo.getCcNo());
			tmpVo.setCcNo(decCcNo);
		}
		return encodedList;
	}
	
	public List<RechargeHistoryVO> decryptCcNoListForRecharge(List<RechargeHistoryVO> encodedList){
		for(RechargeHistoryVO tmpVo : encodedList) {
			tmpVo.setCcNo(decryptUtils.decrypt(tmpVo.getCcNo()));
		}
		return encodedList;
	}
	/**
	 * 
	 * ckim (2018. 1. 21. 오후 7:54:07)<br/>
	 * desc   :  ExcelInfo 생성
	 * 
	 * @param objList
	 * @param fileName
	 * @param excelExceptFields
	 * @return
	 */
	public ExcelInfoVO setExcelInfo(List<Object> objList, String fileName, List<String> excelExceptFields){
		ExcelInfoVO excelInfo = new ExcelInfoVO();
		excelInfo.setExcelType(ExcelType.XLSX);
		excelInfo.setFileName(fileName+ "_" + this.getTodayYYMMDD());
		excelInfo.setSheetName(fileName+ "_" + this.getTodayYYMMDD());
		excelInfo.setTitle(fileName+ "_" + this.getTodayYYMMDD());
		excelInfo.setPrintMakeUser(true);
		excelInfo.setPrintMakeDate(true);
		excelInfo.setPrintMakeTime(true);
		excelInfo.setLineDraw(true);
		excelInfo.setSequenceOrder(SequenceOrder.NONE);
		excelInfo.setRowData(objList);
		excelInfo.setExceptFields(this.makeExceptFieldsStr(excelExceptFields));
		
		return excelInfo;
	}
	
	/**
	 * 
	 * ckim (2018. 1. 21. 오후 7:54:10)<br/>
	 * desc   :  ExcelTitleVO 생성
	 * 
	 * @param excelTitleList
	 * @return
	 */
	public ExcelTableTitleVO setExcelTitleVO(List<String> excelTitleList) {
		ExcelTableTitleVO excelTitleVO = new ExcelTableTitleVO();
		for(String tmpExcelTitle : excelTitleList) {
			excelTitleVO.addTitle(0, new ExcelTitleVO(tmpExcelTitle));
		}

		return excelTitleVO;
	}
	
	/**
	 * 
	 * ckim (2018. 1. 21. 오후 7:54:16)<br/>
	 * desc   :  ExcelExceptFields String 생성
	 * 
	 * @param excelExceptFields
	 * @return
	 */
	private String makeExceptFieldsStr(List<String> excelExceptFields) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0 ; i < excelExceptFields.size() ; i++) {
			if(i == excelExceptFields.size()-1) {
				builder.append(excelExceptFields.get(i));
			} else {
				builder.append(excelExceptFields.get(i)).append(",");
			}
		}
		
		return builder.toString();
	}
	
	/**
	 * 
	 * ckim (2018. 2. 2. 오후 2:29:39)<br/>
	 * desc   :  
	 * 
	 * @param fullPathFlNm
	 * @return
	 */
	public String getFlNmFromFullPathFlNm(String fullPathFlNm) {
		
		String[] splitedOrgFileName = fullPathFlNm.split("/");
		String fileName = splitedOrgFileName[splitedOrgFileName.length-1];
		
		return fileName;
	}
	
	/**
	 * 유효 날짜 검증용
	 * ckim (2018. 2. 13. 오후 7:29:32)<br/>
	 * desc   :  yyyyMMdd 형태 확인
	 * 
	 * @param inDate
	 * @return
	 */
	public boolean isValidDateYYYYMMDD(String inDate) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyMMdd");
//		SimpleDateFormat dateFormatDash = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat dateFormatDash2 = new SimpleDateFormat("yy-MM-dd");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	
	/**
	 * 
	 * ckim (2018. 2. 20. 오전 11:30:34)<br/>
	 * desc   :  원하는 date를 String형태로 반환
	 * 
	 * @param date
	 * @param dateformat
	 * @return
	 */
	public static String makeDttmStr(Date date, String dateformat) {
		SimpleDateFormat transFormat = new SimpleDateFormat(dateformat);
		return transFormat.format(date);
	}
	
	/**
	 * 
	 * ckim (2018. 2. 23. 오후 10:59:51)<br/>
	 * desc   :  CcAcqrr 관련 정보가 유효한지 확인 
	 * 
	 * @param tmpCcAcqrr
	 * @return
	 */
	public boolean isValidCcAcqrr(CommBilngUnmtchVO tmpCcAcqrr) {
		return tmpCcAcqrr != null && StringUtils.isNotBlank(tmpCcAcqrr.getCcAcqrrNm())
				&& StringUtils.isNotBlank(tmpCcAcqrr.getBizRgstNo())
				&& StringUtils.isNotBlank(tmpCcAcqrr.getMrcnNo());
	}
	
}
