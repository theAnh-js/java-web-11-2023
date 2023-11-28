package com.fuku.controller.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.fuku.util.Constant;

@WebServlet(urlPatterns = {"/image"})
public class DownLoadImageController extends HttpServlet {
	private static final long serialVersionUID = -6032540307344667233L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String fileName = req.getParameter("fname");
		File file = new File(Constant.DIR + "/" + fileName);
		
		resp.setContentType("image/jpeg");
		if(file.exists()) {
			IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
		}
	}
	// ví dụ với category:
	// fileName sẽ nhận vào là /category/123456.jpg
	// new file --> c:/category/123456.jpg
	// kiểm tra nếu file này có tồn tại trong máy -> copy lên resp.getOutputStream() để trả ra browser
}
