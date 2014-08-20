package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FazTudo
 */
@WebServlet("/executa")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tarefa = request.getParameter("tarefa");
		if (tarefa == null){
			throw new IllegalArgumentException("Você esqueceu de informar a tarefa.");
		}
		String nomeDaClasse = "br.com.alura.gerenciador.web."+tarefa;
		Class type;
		try {
			type = Class.forName(nomeDaClasse);
			Tarefa t = (Tarefa) type.newInstance();
			String pagina = t.executa(request, response);
			request.getRequestDispatcher(pagina).forward(request, response);
		} catch (Exception e ) {
			throw new ServletException(e);
		}		
	}
}
