package com.lj.support.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lj.support.common.util.CodeConstants;
import com.lj.support.common.util.CodeConstants.AccessChannelCode;
import com.lj.support.common.util.CodeConstants.CookieName;
import com.lj.support.common.util.CodeConstants.SiteClass;
import com.lj.support.security.vo.LoginAgentInfoVO;
import com.lj.support.security.vo.LoginMemberInfoVO;
import com.lj.support.security.vo.LoginUserInfoVO;

/**
 * <pre>
 * <B>lj.pss.support</B>
 *     |_ BaseController.java
 * </pre>
 * 
 *  @author  : jhbang
 *  @date    : 2017. 6. 2. 오후 12:44:17 
 *  @version : 1.0
 *  @desc    : Controller 클래스에서 공통으로 사용할 기능을 모아놓은 클래스<br/>
 *  모든 Controller 클래스는 BaseController를 상속받아 작성한다.
 */
public class BaseController {
	@Value("#{localProperty}")
	protected Properties properties;

	@Value("#{localProperty['comm.currentstage']}")
	protected String currentStage;

	@Value("#{localProperty['server.protocol']}")
	private String	serverProtocol;

	@Value("#{localProperty['server.host']}")
	private String	serverHost;

	@Value("#{localProperty['server.port']}")
	private int		serverPort;

	@Value("#{localProperty['server.protocol.ssl']}")
	private String	serverProtocolSSL;

	@Value("#{localProperty['server.host.ssl']}")
	private String	serverHostSSL;

	@Value("#{localProperty['server.port.ssl']}")
	private int		serverPortSSL;

	@Value("#{localProperty['image.server.host']}")
	private String	imageServerHost;

	@Value("#{localProperty['image.server.port']}")
	private int		imageServerPort;

	/**
	 * jhbang (2017. 8. 22. 오후 12:45:19)<br/>
	 * desc   :  리퀘스트 정보 획득
	 * @param  
	 * @return HttpServletRequest
	 */
	public HttpServletRequest getRequest() {
		RequestAttributes	requestAttributes	= RequestContextHolder.currentRequestAttributes();
		return ((ServletRequestAttributes) requestAttributes).getRequest();
	}

	/**
	 * jhbang (2017. 8. 22. 오후 12:45:34)<br/>
	 * desc   :  세션 정보 획득
	 * @param  
	 * @return HttpSession
	 */
	public HttpSession getSession() {
		return this.getRequest().getSession();
	}

	/**
	 * jhbang (2017. 8. 22. 오후 12:45:45)<br/>
	 * desc   :  접속 IP 획득
	 * @param  
	 * @return String
	 */
	public String getRemoteAddr() {
		return this.getRequest().getRemoteAddr();
	}

	/**
	 * jhbang (2017. 8. 22. 오후 12:45:57)<br/>
	 * desc   :  DB Table 공통 필드 데이터 설정<br/>
	 * 등록자 ID, 등록자 명, 등록 IP, 등록일시, 등록 채널 코드<br/>
	 * 수정인 ID, 수정인 명, 수정 IP, 수정일시, 수정 채널 코드
	 * @param  
	 * @return Object
	 */
	public Object setUserInfo(Object obj) {
		if (null == obj) {
			return null;
		}

		// 로그인 사용자 정보 조회
		LoginMemberInfoVO	memberInfo	= (LoginMemberInfoVO)getSession().getAttribute(CodeConstants.AUTH_MEMBER_SESSION);
		LoginAgentInfoVO	agentInfo	= (LoginAgentInfoVO)getSession().getAttribute(CodeConstants.AUTH_AGENT_SESSION);
		LoginUserInfoVO		userInfo	= (LoginUserInfoVO)getSession().getAttribute(CodeConstants.AUTH_USER_SESSION);

		String	userId		= null;
		String	userNm		= null;
		String	userIp		= getRemoteAddr();
		String	chnlCode	= null;
		Date	curDate		= new Date();

		if (null != memberInfo) {
			userId		= memberInfo.getId();
			userNm		= memberInfo.getMbrNm();
			chnlCode	= memberInfo.getLoginChnlCd();
		} else if (null != agentInfo) {
			userId		= agentInfo.getAgentId();
			userNm		= agentInfo.getAgentLnm() + agentInfo.getAgentFnm();
			chnlCode	= agentInfo.getLoginChnlCd();
		} else if (null != userInfo) {
			userId		= userInfo.getUsrId();
			userNm		= userInfo.getUsrNm();
			chnlCode	= userInfo.getLoginChnlCd();
		} else {
			userId		= "SYSTEM";
			userNm		= "SYSTEM";
			chnlCode	= "SVC";
		}

		if (StringUtils.isEmpty(chnlCode)) {
			chnlCode	= getAccessChannelCode();
		}

		Method[]	methods	= obj.getClass().getDeclaredMethods();
		Object[]	param	= null;

		for (int i = 0 ; i < methods.length; i++) {
			if (("setRgtntId".equalsIgnoreCase(methods[i].getName()) || "setUpdrId".equalsIgnoreCase(methods[i].getName()))
					&& (1 == methods[i].getParameterTypes().length)) {
				param	= new Object[] { userId };
			} else if (("setRgtntNm".equalsIgnoreCase(methods[i].getName()) || "setUpdrNm".equalsIgnoreCase(methods[i].getName()))
						&& (1 == methods[i].getParameterTypes().length)) {
					param	= new Object[] { userNm };
	        } else if (("setRgstIp".equalsIgnoreCase(methods[i].getName()) || "setUpdIp".equalsIgnoreCase(methods[i].getName()))
					&& (1 == methods[i].getParameterTypes().length)) {
				param	= new Object[] { userIp };
	        } else if (("setRgstChnlCd".equalsIgnoreCase(methods[i].getName()) || "setUpdChnlCd".equalsIgnoreCase(methods[i].getName()))
						&& (1 == methods[i].getParameterTypes().length)) {
				param	= new Object[] { chnlCode };
	        } else if (("setRgstDttm".equalsIgnoreCase(methods[i].getName()) || "setUpdDttm".equalsIgnoreCase(methods[i].getName()))
						&& (1 == methods[i].getParameterTypes().length)) {
				param	= new Object[] { curDate };
	        }

			if (null != param) {
				try {
					methods[i].invoke(obj, param);
					param	= null;
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					param	= null;
				}
			}
	    }

		return obj;
	}

	/**
	 * jhbang (2017. 8. 22. 오후 12:45:03)<br/>
	 * desc   :  모바일 기기 접속 여부 확인
	 * @param  
	 * @return boolean
	 */
	public boolean isMobileOS() {
		//모바일 체크
		String	ua			= getRequest().getHeader("User-Agent").toLowerCase(Locale.ENGLISH);
		boolean	isMobileOS	= false;

		if (ua.matches(".*(android|avantgo|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|symbian|treo|up\\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino).*")
				|| ua.substring(0,4).matches("1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|e\\-|e\\/|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\\-|2|g)|yas\\-|your|zeto|zte\\-")) {
			isMobileOS	= true; 

			if (ua.indexOf("v901") > -1 || ua.indexOf("v500") > -1 || ua.indexOf("v525") > -1 || ua.indexOf("lg-v700n") > -1 || ua.indexOf("lg-v607l") > -1) { 
				isMobileOS	= false;	
			}
		}

		return isMobileOS;
	}

	/**
	 * jhbang (2017. 8. 30. 오전 10:28:31)<br/>
	 * desc   :  현재 접속 채널 반환
	 * @param  
	 * @return String
	 */
	public String getAccessChannelCode() {
		if (isMobileOS()) {
			String	chnlCd	= StringUtils.defaultIfEmpty((String)getSession().getAttribute(CodeConstants.ACCESS_CHANNEL_SESSION), AccessChannelCode.MOBILE_WEB);

			if (AccessChannelCode.MOBILE_APP.equals(chnlCd)) {
				return AccessChannelCode.MOBILE_APP;
			} else {
				return AccessChannelCode.MOBILE_WEB;
			}
		} else {
			return AccessChannelCode.PC_WEB;
		}
	}

	/**
	 * jhbang (2017. 8. 30. 오후 2:33:03)<br/>
	 * desc   :  시스템의 현재 설정 언어 조회
	 * @param  
	 * @return String
	 */
	public String getCurrentLangCd(String siteCls) {
		Cookie[]	cookies	= getRequest().getCookies();
		String		result	= "";
		boolean		isFO	= SiteClass.FO.equals(siteCls);
		boolean		isAGT	= SiteClass.AGT.equals(siteCls);

		if ((null != cookies) && (0 < cookies.length)) {
			for (Cookie cookie : cookies) {
				if (isFO && CookieName.FO_LANG_CD.equals(cookie.getName())) {
					result	= cookie.getValue();
					break;
				}
			}

			if (StringUtils.isEmpty(result)) {
				for (Cookie cookie : cookies) {
					if (isAGT && CookieName.AGT_LANG_CD.equals(cookie.getName())) {
						result	= cookie.getValue();
						break;
					}
				}
			}

			if (StringUtils.isEmpty(result)) {
				for (Cookie cookie : cookies) {
					if (CookieName.BO_LANG_CD.equals(cookie.getName())) {
						result	= cookie.getValue();
						break;
					}
				}
			}
		}

		if (StringUtils.isEmpty(result)) {
			result	= CodeConstants.DEFAULT_LANG_CD;
		}

		return result;
	}

	/**
	 * jhbang (2017. 12. 27. 오후 1:31:55)<br/>
	 * desc   :  이미지 서버 도메인 정보 설정
	 * 
	 * @return
	 */
	public String getImageServerUri() {
		StringBuffer	uri	= new StringBuffer(1024);

		uri.append(this.imageServerHost);

		if ((80 != this.imageServerPort) && (443 != this.imageServerPort)) {
			uri.append(":").append(this.imageServerPort);
		}

		return uri.toString();
	}

	/**
	 * jhbang (2018. 1. 2. 오후 7:33:39)<br/>
	 * desc   :  서버 도메인 정보 설정
	 * 
	 * @return
	 */
	public String getServerUri() {
		StringBuffer	uri	= new StringBuffer(1024);

		if (StringUtils.isNotEmpty(this.serverProtocol)) {
			uri.append(this.serverProtocol).append("://");
		}

		uri.append(this.serverHost);

		if ((80 != this.serverPort) && (443 != this.serverPort)) {
			uri.append(":").append(this.serverPort);
		}

		return uri.toString();
	}

	/**
	 * jhbang (2018. 1. 2. 오후 7:33:50)<br/>
	 * desc   :  서버 도메인 SSL 정보 설정
	 * 
	 * @return
	 */
	public String getServerSSLUri() {
		StringBuffer	uri	= new StringBuffer(1024);

		if (StringUtils.isNotEmpty(this.serverProtocolSSL)) {
			uri.append(this.serverProtocolSSL).append("://");
		}

		uri.append(this.serverHostSSL);

		if ((80 != this.serverPortSSL) && (443 != this.serverPortSSL)) {
			uri.append(":").append(this.serverPortSSL);
		}

		return uri.toString();
	}
}
