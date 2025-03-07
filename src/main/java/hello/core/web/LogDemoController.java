package hello.core.web;

import hello.core.common.MyLogger;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

	private final LogDemoService logDemoService;
	//	private final ObjectProvider<MyLogger> myLoggerProvider; // Dependency Lookup
	private final MyLogger myLogger;

	@RequestMapping("log-demo")
	@ResponseBody
	public String logDemo(HttpServletRequest request) {
//		MyLogger myLogger = myLoggerProvider.getObject();
		String requestUrl = request.getRequestURL().toString();
		myLogger.setRequestUrl(requestUrl);
		myLogger.log("controller test");
		logDemoService.logic("testId");
		return "OK";
	}

}
