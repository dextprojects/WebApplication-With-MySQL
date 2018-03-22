package com.dext.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection conn = null;
	Statement stmt = null;
	ResultSet rst = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Context initContext;
		try {
			initContext = new InitialContext();
			if (initContext == null)
				throw new Exception("Boom - No Context");
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/mssql");
			if (ds != null) {
				conn = ds.getConnection();
				if (conn != null) {
					stmt = conn.createStatement();
					conn.setAutoCommit(false);
					String option = request.getParameter("option") == null ? "" : request.getParameter("option");
					response.setCharacterEncoding("UTF-8");

					if (option.equalsIgnoreCase("loginToWeb")) {
						loginToWeb(request, response);
					} else if (option.equalsIgnoreCase("doTask")) {

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rst != null) {
				try {
					rst.close();
				} catch (SQLException e) {
					System.out.println("Exception in Login " + e);
				}
				rst = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("Exception in Login " + e);
				}
				stmt = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Exception in Login " + e);
				}
				conn = null;
			}
		}
	}

	private void loginToWeb(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String parameters = request.getParameter("parameters") == null ? "" : request.getParameter("parameters");
		System.out.println("Parameters: "+parameters);
		String query = "";
		rst = stmt.executeQuery(query);
		if (rst.next()) {

		}
	}

}
