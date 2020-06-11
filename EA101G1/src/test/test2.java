package test;



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

import com.productType.model.PtDAO;
import com.productType.model.PtVO;


public class test2 {

	public static void main(String[] args) {

		PtDAO dao = new PtDAO();
//		
////			
//			PtVO ptVO1 = new PtVO();
//			ptVO1.setPt_id("test");
//			ptVO1.setTypename("����");
//			dao.insert(ptVO1);
//			System.out.println("�s�W���\");
		

//		// �ק�
//
//			
//			PtVO ptVO1 = new PtVO();
//			ptVO1.setPt_id("test");
//			ptVO1.setTypename("weeeeeeeeeeeeeeeeeeeed");
//			
//			dao.update(ptVO1);
//			System.out.println("��s���\");
//		
//
//		// �R��
//		dao.delete("test");
//
//		// �d��
//		PtVO ptVO1 = dao.findByPrimaryKey("test");
//		System.out.print(ptVO1.getPt_id() + ",");
//		System.out.print(ptVO1.getTypename() + ",");
//		System.out.println("---------------------");
//
//		// �d��
		List<PtVO> list = dao.getAll();
		for (PtVO pro : list) {
			System.out.print(pro.getPt_id() + ",");
			System.out.println(pro.getTypename());
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
