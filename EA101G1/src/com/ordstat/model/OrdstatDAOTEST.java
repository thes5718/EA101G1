package com.ordstat.model;

import java.util.List;

public class OrdstatDAOTEST {

	public static void main(String[] args) {
		OrdstatDAO dao = new OrdstatDAO();
		
		//新增
//		OrdstatVO ordstatVO1 = new OrdstatVO();
//		ordstatVO1.setOrdstat("要收貨唷");
//		
//		dao.insert(ordstatVO1);
//		System.out.println("新增成功");
		
		//修改
//		OrdstatVO ordstatVO2 = new OrdstatVO();
//		ordstatVO2.setOrdstat_id("018");
//		ordstatVO2.setOrdstat("已修改");
//	
//		dao.update(ordstatVO2);
//		System.out.println("修改成功");
		
		//刪除
//		dao.delete("018");
//		System.out.println("刪除成功");
		
		//查詢
		List<OrdstatVO> list = dao.getAll();
		for(OrdstatVO aOrdstat : list) {
			System.out.println(aOrdstat.getOrdstat_id() + ",");
			System.out.println(aOrdstat.getOrdstat() + ",");
			System.out.println();
		}
		System.out.println("查詢結束");
		
	}

}
