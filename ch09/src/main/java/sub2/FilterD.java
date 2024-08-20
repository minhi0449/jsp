package sub2;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = {"/welcome.do", "/greeting.do"})
public class FilterD implements Filter{
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {

		System.out.println("FilterD doFilter...");
		
		// 다음 필터로 이동 (FilterChain arg2)
		// 개별 필터들을 연결했다 - 체이닝 chaining
		arg2.doFilter(arg0, arg1);
		
	}
	
	
	
}
