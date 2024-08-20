package sub2;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

// "/hello.do" 에 대해서만 filtering 을 하겠다!
@WebFilter("/hello.do")
public class FilterC implements Filter{
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {

		System.out.println("FilterC doFilter...");
		
		// 다음 필터로 이동 (FilterChain arg2)
		arg2.doFilter(arg0, arg1);
	}
	
}
