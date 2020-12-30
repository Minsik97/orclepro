package com.javaex.phone;

import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {
		
		int num, num2;
		String name, hp, company;
		
		PhoneDao phoneDao = new PhoneDao();
		List<PhoneVo> phoneList;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("**************************************");
		System.out.println("*        전화번호 관리 프로그램        *");
		System.out.println("**************************************");
		
		
		/*테스트용 데이터 넣기
		PhoneVo phoneVo01 = new PhoneVo ("이효리", "010-1111-1111",  "02-1111-1111");
		phoneDao.personInsert(phoneVo01);
		
		PhoneVo phoneVo02 = new PhoneVo ("정우성", "010-2222-2222",  "02-2222-2222");
		phoneDao.personInsert(phoneVo02);
		
		PhoneVo phoneVo03 = new PhoneVo ("유재석", "010-3333-3333",  "02-3333-3333");
		phoneDao.personInsert(phoneVo03);
		
		PhoneVo phoneVo04 = new PhoneVo ("이정재", "010-4444-4444",  "02-4444-4444");
		phoneDao.personInsert(phoneVo04);
		
		PhoneVo phoneVo05 = new PhoneVo ("서장훈", "010-5555-5555",  "02-5555-5555");
		phoneDao.personInsert(phoneVo05);
		
		PhoneVo phoneVo06 = new PhoneVo ("강호동", "010-6666-6666",  "02-6666-6666");
		phoneDao.personInsert(phoneVo06);
		*/
		
		/*테스트용 업데이트
		PhoneVo pupVo = new PhoneVo(5, "서장훈", "010-5555-5555", "02-5555-5555");
		phoneDao.personUpdate(pupVo);
		*/
		
		/*테스트용 삭제기능
		phoneDao.phoneDelete(1);
		*/
		
		/*테스트용 리스트 출력 
		phoneList = phoneDao.getPersonList();
		for(int i=0; i < phoneList.size(); i++) {
			PhoneVo vo = phoneList.get(i);
			System.out.println(vo.getPhoneId() + ", " + vo.getName()+ ", " + vo.getHp() + ", " + vo.getCompany());
		}
		*/
		
		while(true) {
			System.out.println("1.리스트    2.등록    3.수정    4.삭제    5.검색    6.종료");
			System.out.println("-------------------------------------------------");
			System.out.println(">메뉴번호:");
			num = sc.nextInt();
			
			if(num == 6) {
				
				System.out.println("**************************************");
				System.out.println("*                 감사합니다.                 *");
				System.out.println("**************************************");
				break;
			} else if (num == 1) {
				System.out.println("1.리스트>");
				phoneList = phoneDao.getPersonList();
				for(int i=0; i < phoneList.size(); i++) {
					PhoneVo vo = phoneList.get(i);
					System.out.println(vo.getPhoneId() + ", " + vo.getName()+ ", " + vo.getHp() + ", " + vo.getCompany());
				}
				
				System.out.println("");
				
			} else if(num == 2) {
				System.out.println("<2.등록>");
				
				System.out.print(">이름: ");
				name = sc.next();
				System.out.print(">핸드폰: ");
				hp = sc.next(); 
				System.out.print(">회사번호: ");
				company = sc.next();
				
				PhoneVo phoneVo = new PhoneVo(name, hp, company);
				phoneDao.personInsert(phoneVo);
				
				System.out.println("");
				
			} else if(num == 3) {
				System.out.println("<3.수정>");
				
				System.out.print(">번호: ");
				num2 = sc.nextInt();
				sc.nextLine();
				System.out.print(">이름: ");
				name = sc.next();
				System.out.print(">핸드폰: ");
				hp = sc.next();
				System.out.print(">회사번호: ");
				company = sc.next();
				
				PhoneVo pupVo = new PhoneVo(num2, name, hp, company);
				phoneDao.personUpdate(pupVo);
				
				System.out.println("");
				
			} else if(num == 4) {
				System.out.println("<4.삭제>");
				
				System.out.print(">번호: ");
				num2 = sc.nextInt();
				sc.next();
				
				phoneDao.phoneDelete(num2);
				
				System.out.println("");
				
			}
			
			
			
		}
		
		
	}

}
