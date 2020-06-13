package test;






import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.favouriteProduct.model.FavpDAO;
import com.favouriteProduct.model.FavpVO;



public class test5 {

	public static void main(String[] args) {

		FavpDAO dao = new FavpDAO();

		
			// �s�W
			
//			FavpVO favpVO = new FavpVO();
//			favpVO.setP_id("P009");
//			favpVO.setMem_id("M000001");
//			dao.insert(favpVO);
//			System.out.println("�s�W���\");
//		
		

//
//		// �R��
//		dao.delete("P009","M000001");
//

//		// �d��
		List<FavpVO> list = dao.getFavpByMem("M000001");
		for (FavpVO po : list) {
			System.out.print(po.getP_id() + ",");
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
