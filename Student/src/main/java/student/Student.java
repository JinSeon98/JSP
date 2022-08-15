package student;

import java.util.List;
import java.util.Scanner;

import student.studentdao.StudentDAO;
import student.studentdto.StudentDTO;

public class Student {
	private final Scanner sc = new Scanner(System.in);
	
	public Student() {
		menu();
	}
	
	private void menu() {
		//무한루프를 계속 돌면 아래의 코드에 도달하지 못해서 Unreachable code오류 발생
		while(true) {
			System.out.print("****************\r\n"
					+ "   관리\r\n"
					+ "****************\r\n"
					+ "  1. 입력\r\n"
					+ "  2. 수정\r\n"
					+ "  3. 삭제\r\n"
					+ "  4. 검색\r\n"
					+ "  5. 종료\r\n"
					+ "****************\r\n"
					+ "  번호선택 : ");
			int choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				insertArticle();
				break;
			case 2:
				updateArticle();
				break;
			case 3:
				deleteArticle();
				break;
			case 4:
				selectArticle();
				break;
			case 5:
				exit();
			default:
				System.out.println("잘못된 메뉴 입력!");
			}
		}
	}
	private void exit() {
		System.out.println("프로그램이 종료됩니다!");
		sc.close();
		System.exit(0); //프로그램 정상종료시 사용
		//비정상 종료 - System.exit(1);
	}
	
	//1. 입력
	private void insertArticle() {
		int code = codeInput();
		if(code == 4) {
			System.out.println("이전 메뉴로..");
			return;
		}else if(code < 1 || code > 4) {
			System.out.println("잘못된 선택! 이전 메뉴로..");
			return;
		}
		
		String name = nameInput("");
		String value = valueInput(code);
		
		StudentDTO dto = new StudentDTO(name, value, code);
		StudentDAO dao = new StudentDAO();
		
		boolean check = dao.insert(dto);
		if(check) {
			System.out.println(name + "님의 정보가 추가 되었습니다.");
		}else {
			System.out.println("정보 추가에 실패하였습니다.");
		}
	}
	private int codeInput() {
		System.out.print("****************\r\n"
				+ "   1. 학생\r\n"
				+ "   2. 교수\r\n"
				+ "   3. 관리자\r\n"
				+ "   4. 이전메뉴\r\n"
				+ "****************\r\n"
				+ "   번호선택 : ");
		int code = sc.nextInt();
		return code;
	}
	private String nameInput(String msg) {
		System.out.print( msg + "이름 입력 : ");
		String name = sc.next();
		return name;
	}
	private String valueInput(int code) {
		if(code == 1) {
			System.out.print("학번 입력 : ");
		}else if(code == 2) {
			System.out.print("과목 입력 : ");
		}else if(code == 3) {
			System.out.print("부서 입력 : ");
		}
		
		return sc.next();
	}
	
	//2. 수정
	private void updateArticle() {
		String name = nameInput("수정할 ");
		
		StudentDAO dao = new StudentDAO();
		StudentDTO dto = dao.selectOne(name);
		
		if(dto == null) {
			System.out.println("검색 실패! 이전 메뉴로..");
			return;
		}
		
		System.out.println(dto.getName() + "님의 정보");
		System.out.println(valueString(dto.getCode()) + dto.getValue());
		
		int code = codeInput();
		
		if(code == 4) {
			System.out.println("이전 메뉴로..");
			return;
		}else if(code < 1 || code > 4) {
			System.out.println("잘못된 선택! 이전 메뉴로...");
			return;
		}
		
		String value = valueInput(code);
		
		dto.setValue(value);
		dto.setCode(code);
		
		int su = dao.update(dto);
		
		if(su != 0) {
			System.out.println(dto.getName() + "님의 정보가 수정되었습니다.");
		}else {
			System.out.println("정보 수정에 실패하였습니다.");
		}
	}
	private String valueString(int code) {
		if(code == 1) {
			return "학번 : ";
		}else if(code == 2) {
			return "과목 : ";
		}else{
			return "부서 : ";
		}
	}
	
	//3. 삭제
	private void deleteArticle() {
		String name = nameInput("삭제할 ");
		
		StudentDAO dao = new StudentDAO();
		int su = dao.delete(name);
		
		if(su != 0) {
			System.out.println(name + "님의 정보가 삭제되었습니다.");
		}else {
			System.out.println("정보 삭제에 실패하였습니다.");
		}
	}
	
	//4. 검색
	private void selectArticle() {
		 System.out.print("****************\r\n" + 
                 "   1. 이름 검색\r\n" + 
                 "   2. 부서 검색\r\n" + 
                 "   3. 전체 검색\r\n" + 
                 "   4. 이전메뉴\r\n" + 
                 "****************\r\n" + 
                 "   번호선택 : ");
		 int select = sc.nextInt();
		 
		 if(select == 4) {
			 System.out.println("이전메뉴로..");
			 return;
		 }else if(select < 1 || select > 4) {
			 System.out.println("잘못된 선택!! 이전 메뉴로..");
			 return;
		 }
		 StudentDTO dto = null;
		 //이름 검색
		 if(select == 1) {
			 dto = new StudentDTO();
			 dto.setName(nameInput("검색할 "));
		 }else if(select == 2) { 
			 int code = codeInput();
			 
			 if(select == 4) {
                 System.out.println("이전메뉴로..");
                 return;
			 }else if(select <1 || select > 4) {
                 System.out.println("잘못된 선택!! 이전 메뉴로..");
                 return;
			 }
			 //잘못된 코드값이 들어올 수 있기 때문에 코드값 입력받고 객체 생성.
			 dto = new StudentDTO();
			 dto.setCode(code);
		 }
		 
		 StudentDAO dao = new StudentDAO();
		 
		 List<StudentDTO> list = dao.select(dto);
		 
		 if(list == null) {
			 System.out.println("검색 결과가 없습니다!!");
		 }else {
			 for(StudentDTO d : list) {
				 System.out.println("이름 : " + d.getName() + "\t" + valueString(d.getCode()) + d.getValue());
			 }
		 }
		 
	}
}
