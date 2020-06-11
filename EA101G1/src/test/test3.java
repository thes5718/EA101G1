package test;



import com.productOrder.model.PoDAO;
import com.productOrder.model.PoVO;

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


public class test3 {

	public static void main(String[] args) {

		PoDAO dao = new PoDAO();
//		
		
			// �s�W
			
//			PoVO poVO1 = new PoVO();
//			poVO1.setMem_no("M000001");
//			poVO1.setOrdstat_id("001");
//			dao.insert(poVO1);
//			System.out.println("�s�W���\");
//		
		

//		// �ק�
//		
////			
//			PoVO poVO1 = new PoVO();
//			poVO1.setReturn_form("���X��");
//			poVO1.setOrdstat_id("011");
//			poVO1.setPo_id("2020-06-11-000025");
//			dao.update(poVO1);
//			System.out.println("��s���\");
		
//
//		// �R��
//		dao.delete("2020-06-11-000026");
//
//		// �d��
//		PoVO poVO1 = dao.findByPrimaryKey("2020-06-11-000027");
//		System.out.print(poVO1.getPo_id() + ",");
//		System.out.print(poVO1.getMem_id() + ",");
//		System.out.print(poVO1.getOrdstat_id() + ",");
//		System.out.print(poVO1.getAdd_date() + ",");
//		System.out.print(poVO1.getReturn_form() + ",");
//		System.out.println("---------------------");

//		// �d��
		List<PoVO> list = dao.getAll();
		for (PoVO po : list) {
			System.out.print(po.getPo_id() + ",");
			System.out.print(po.getMem_id() + ",");
			System.out.print(po.getOrdstat_id() + ",");
			System.out.print(po.getAdd_date() + ",");
			System.out.print(po.getReturn_form() + ",");
			System.out.println("---------------------");
			System.out.println();
		}
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
