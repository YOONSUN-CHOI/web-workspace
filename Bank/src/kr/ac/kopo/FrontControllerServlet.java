package kr.ac.kopo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontControllerServlet extends HttpServlet {

	private HandlerMapping mappings;

	@Override
	public void init(ServletConfig config) throws ServletException {
		String propLoc = config.getInitParameter("propLocation");
		mappings = new HandlerMapping(propLoc);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String context = request.getContextPath();
		String uri = request.getRequestURI();
		uri = uri.substring(context.length());

		System.out.println("요청 uri: " + uri);

		try {
			Controller control = mappings.getController(uri);

			if (control != null) {

				//String callPage = control.handleRequest(request, response);

				// 기존방식
				// RequestDispatcher dispatcher = request.getRequestDispatcher(callPage);
				// dispatcher.forward(request, response);

				// sendRedirect를 쓰기 위한 방식 (forward 시키지 말고 내 url이 바뀌어야 하는 상황(sendRedirect))

				if (control != null) {
					String callPage = control.handleRequest(request, response);

					if (callPage.startsWith("redirect:")) {
						System.out.println("redirect : " + callPage.substring("redirect:".length()));
						response.sendRedirect(callPage.substring("redirect:".length()));
					} else {
						RequestDispatcher dispatcher = request.getRequestDispatcher(callPage);
						dispatcher.forward(request, response);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}