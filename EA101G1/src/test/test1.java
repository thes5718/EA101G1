package test;

import com.product.model.ProDAO;
import com.product.model.ProVO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class test1 {

	public static void main(String[] args) {

		ProDAO dao = new ProDAO();
//		
		try {
			byte[] pic = getPictureByteArray("items/product1.jpg");
			// �s�W
			
			ProVO proVO1 = new ProVO();
			proVO1.setPt_id("PT001");
			proVO1.setP_name("�ܻy�Q�v");
			proVO1.setP_price(new Double(20000));
			proVO1.setP_image(pic);
			proVO1.setP_info("�[�c����");
			proVO1.setP_sales(10);
			proVO1.setP_stock(200);
			proVO1.setP_stat(0);
			dao.insert(proVO1);
			System.out.println("�s�W���\");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

//		// �ק�
//		try {
//			byte[] pic = getPictureByteArray("items/tomcat.png");
//			
//			ProVO proVO1 = new ProVO();
//			proVO1.setPt_id("PT001");
//			proVO1.setP_name("�c�]�ߨk");
//			proVO1.setP_price(new Double(87));
//			proVO1.setP_image(pic);
//			proVO1.setP_info("�A���ߪ��c��");
//			proVO1.setP_sales(69);
//			proVO1.setP_stock(81000);
//			proVO1.setP_stat(0);
//			proVO1.setP_id("P018");
//			dao.update(proVO1);
//			System.out.println("��s���\");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//
//		// �R��
//		dao.delete("P018");
//
//		// �d��
//		ProVO proVO1 = dao.findByPrimaryKey("P001");
//		System.out.print(proVO1.getP_id() + ",");
//		System.out.print(proVO1.getP_name() + ",");
//		System.out.print(proVO1.getP_price() + ",");
//		System.out.print(proVO1.getP_info() + ",");
//		System.out.print(proVO1.getP_sales() + ",");
//		System.out.print(proVO1.getP_stock() + ",");
//		System.out.println(proVO1.getP_add_date()+",");
//		System.out.println(proVO1.getP_stat());
//		System.out.println("---------------------");
//
//		// �d��
//		List<ProVO> list = dao.getAll();
//		for (ProVO pro : list) {
//			System.out.print(pro.getP_id() + ",");
//			System.out.print(pro.getP_name() + ",");
//			System.out.print(pro.getP_price() + ",");
//			System.out.print(pro.getP_info() + ",");
//			System.out.print(pro.getP_sales() + ",");
//			System.out.print(pro.getP_stock() + ",");
//			System.out.println(pro.getP_add_date()+",");
//			System.out.println(pro.getP_stat());
//			System.out.println("---------------------");
//			System.out.println();
//		}
	}

	public static InputStream getPictureStream(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		return fis;
	}

	// �ϥ�byte[]�覡
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); // ��X��t�ιw�]��byte�}�C
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray(); // �^�ǹϤ��줸���
	}
}
