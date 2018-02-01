package IteratorPackage;

public class Client {

	public static void main(String[] args) {
		/**
		 * case)
		 *  여행정보를 제공받는다고 할때, 
		 *  고객사와 제공회사가 다르다고 할 경우 고객이 forloop를 자료구조에 따라 바꾸지 않아도 됨.
		 *  고객사에게 캡슐화 되어 넘어감. 알필요도 없고 공통성격이라 좋고 1석2조?
		 *  제공회사가 사정에 의해 자료구조를 바꿨다고 할 경우...난감.(고객사가 1000개)
		 *  
		 *  
		 */
		
		Aggregate suwon = new Suwon();
		Aggregate seoul = new Seoul();
		Aggregate busan = new Busan();
		
		//수원명소 
		System.out.println("----Suwon----");
		print(suwon.iterator());
		//서울명소
		System.out.println("----Seoul----");
		print(seoul.iterator());
		//부산명소
		System.out.println("----Busan----");
		print(busan.iterator());
		
		/**
		 * 결과
		 * ----Suwon----
			광교산
			수원성
			중평떡
			수원갈비
			----Seoul----
			경복궁
			한강
			뭐가있지?
			----Busan----
			광안리 해수욕장
			해운대 해수욕장
			돼지국밥

		 */
	}
	
	public static void print(IteratorFunc it) {
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}

}


