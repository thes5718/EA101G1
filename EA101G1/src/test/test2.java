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
import java.util.Set;

import com.product.model.ProVO;
import com.productType.model.PtDAO;
import com.productType.model.PtVO;


public class test2 {

	public static void main(String[] args) {

		PtDAO dao = new PtDAO();
//		
////			
//			PtVO ptVO1 = new PtVO();
//			ptVO1.setPt_id("test");
//			ptVO1.setTypename("測試");
//			dao.insert(ptVO1);
//			System.out.println("新增成功");
		

//		// 修改
//
//			
//			PtVO ptVO1 = new PtVO();
//			ptVO1.setPt_id("test");
//			ptVO1.setTypename("weeeeeeeeeeeeeeeeeeeed");
//			
//			dao.update(ptVO1);
//			System.out.println("更新成功");
//		
//
//		// 刪除
//		dao.delete("test");
//
//		// 查詢
//		PtVO ptVO1 = dao.findByPrimaryKey("test");
//		System.out.print(ptVO1.getPt_id() + ",");
//		System.out.print(ptVO1.getTypename() + ",");
//		System.out.println("---------------------");
//
//		// 查詢
//		List<PtVO> list = dao.getAll();
//		for (PtVO pro : list) {
//			System.out.print(pro.getPt_id() + ",");
//			System.out.println(pro.getTypename());
//			System.out.println("---------------------");
//			System.out.println();
//		}
		//查詢
		Set<ProVO> set = dao.getProductByPtid("PT001");
		for (ProVO pro : set) {
			System.out.print(pro.getP_id() + ",");
			System.out.println(pro.getPt_id()+",");
			System.out.print(pro.getP_name() + ",");
			System.out.print(pro.getP_price() + ",");
			System.out.print(pro.getP_info() + ",");
			System.out.print(pro.getP_sales() + ",");
			System.out.print(pro.getP_stock() + ",");
			System.out.println(pro.getP_add_date()+",");
			System.out.println(pro.getP_stat());
			System.out.println("---------------------");
			System.out.println();
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
