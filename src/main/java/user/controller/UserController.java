package user.controller;

import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import user.service.LoanServiceImpl;
import utils.Md5Util;
import utils.RsaUnit;

@Controller
@RequestMapping("/user")
public class UserController {
	private Map<String, PrivateKey> map = new HashMap<String, PrivateKey>();

	@Resource(name = "userService")
	private LoanServiceImpl userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String password = request.getParameter("password");
		System.out.println("密文:" + password);
		String publicKey = request.getParameter("publicKey");
		PrivateKey privateKey = map.get(publicKey);
		// byte[] en_result = new BigInteger(password, 16).toByteArray();
		byte[] en_result = RsaUnit.hexStringToBytes(password);
		byte[] de_result = RsaUnit.decrypt(privateKey, en_result);
		StringBuffer sb = new StringBuffer();
		sb.append(new String(de_result));
		String newpassword = sb.reverse().toString();
		System.out.println(Md5Util.encryptMD5(newpassword.getBytes()));
		// 密钥用完之后删除掉map中的值
		if (map.get(publicKey) != null) {
			map.remove(publicKey);
		}
	}

	@RequestMapping(value = "/getkey", method = RequestMethod.POST)
	public String addkey() throws Exception {
		System.out.println("加密前：" + System.currentTimeMillis());
		// response.setHeader("Charset", "UTF8");
		// response.setCharacterEncoding("UTF-8");
		// response.setContentType("text/html; charset=UTF-8");
		Map<String, String> keyMap = RsaUnit.createKeys(512);
		String publicKeyString = keyMap.get("publicKey");
		String privateKeyString = keyMap.get("privateKey");
		RSAPublicKey rsaPublicKey = RsaUnit.getPublicKey(publicKeyString);
		PrivateKey privateKey = RsaUnit.getPrivateKey(privateKeyString);
		String Modulus = rsaPublicKey.getModulus().toString(16);
		String Exponent = rsaPublicKey.getPublicExponent().toString(16);
		System.out.println("Modulus:" + Modulus);
		System.out.println("Exponent:" + Exponent);
		map.put(Modulus, privateKey);
		// response.getWriter().write(Modulus);
		return Modulus;
	}

	public static void main(String[] args) throws Exception {
		Map<String, String> keyMap = RsaUnit.createKeys(512);
		String publicKeyString = keyMap.get("publicKey");
		String privateKeyString = keyMap.get("privateKey");
		String string = RsaUnit.getPublicKey(publicKeyString).getModulus().toString(16);
		System.out.println(string);
	}
}
