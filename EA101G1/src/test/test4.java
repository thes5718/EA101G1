package test;






import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.productOrderList.model.PolDAO;
import com.productOrderList.model.PolService;
import com.productOrderList.model.PolVO;


public class test4 {

	public static void main(String[] args) {

		PolDAO dao = new PolDAO();

		
			// 新增
			
//			PolVO polVO = new PolVO();
//			polVO.setPo_id("2020-06-11-000001");
//			polVO.setP_id("P003");
//			polVO.setOrder_qua(0);
//			polVO.setP_price(new Double(200));
//			dao.insert(polVO);
//			System.out.println("新增成功");
////		
		

//
//		// 刪除
//		dao.delete("2020-06-11-000001","P003");
//
//		// 查詢
//		PolVO poVO1 = dao.findByPrimaryKey("2020-06-11-000002","P005");
//		System.out.print(poVO1.getPo_id() + ",");
//		System.out.print(poVO1.getP_id() + ",");
//		System.out.print(poVO1.getOrder_qua() + ",");
//		System.out.print(poVO1.getP_price());
//		System.out.println("---------------------");

//		// 查詢
//		List<PolVO> list = dao.getAll();
//		for (PolVO po : list) {
//			System.out.print(po.getPo_id() + ",");
//			System.out.print(po.getP_id() + ",");
//			System.out.print(po.getOrder_qua() + ",");
//			System.out.print(po.getP_price());
//			System.out.println("---------------------");
//			System.out.println();
//		}
			PolService polSvc = new PolService();
			List <PolVO> list = polSvc.getPolbyPoId("2020-06-30-000005");
			for(PolVO polVO: list) {
				System.out.println(polVO.getP_id());
				System.out.println(polVO.getPo_id());
				System.out.println(polVO.getOrder_qua());
				System.out.println(polVO.getP_price());
				System.out.println("-----------------------");
			}
	}

	public static InputStream getPictureStream(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		return fis;
	}

	// 使用byte[]方式
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); // 輸出到系統預設的byte陣列
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray(); // 回傳圖片位元資料
	}
}
