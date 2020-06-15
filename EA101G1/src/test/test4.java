package test;






import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.poductOrderList.model.PolDAO;
import com.poductOrderList.model.PolVO;


public class test4 {

	public static void main(String[] args) {

		PolDAO dao = new PolDAO();

		
			// �s�W
			
			PolVO polVO = new PolVO();
			polVO.setPo_id("2020-06-11-000001");
			polVO.setP_id("P003");
			polVO.setOrder_qua(0);
			polVO.setP_price(new Double(200));
			dao.insert(polVO);
			System.out.println("�s�W���\");
//		
		

//
//		// �R��
//		dao.delete("2020-06-11-000001","P003");
//
//		// �d��
//		PolVO poVO1 = dao.findByPrimaryKey("2020-06-11-000002","P005");
//		System.out.print(poVO1.getPo_id() + ",");
//		System.out.print(poVO1.getP_id() + ",");
//		System.out.print(poVO1.getOrder_qua() + ",");
//		System.out.print(poVO1.getP_price());
//		System.out.println("---------------------");

//		// �d��
//		List<PolVO> list = dao.getAll();
//		for (PolVO po : list) {
//			System.out.print(po.getPo_id() + ",");
//			System.out.print(po.getP_id() + ",");
//			System.out.print(po.getOrder_qua() + ",");
//			System.out.print(po.getP_price());
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
