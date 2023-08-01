import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class hwalgo01_서울_09반_김제영 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); 
		int[] switches = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			switches[i] = Integer.parseInt(st.nextToken());
		
		int stus = Integer.parseInt(br.readLine());
		for(int i=0; i < stus; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int number = Integer.parseInt(st.nextToken());
			
			if(gender == 1) {
				for(int j=0; j<N; j++)
					if((j+1) % number == 0)
						switches[j] = switches[j] == 0? 1: 0;
			}
			else {
				switches[number - 1] = switches[number - 1] == 0 ? 1 : 0;
				for(int j=1; j<N/2; j++) {
					if(number - 1 + j >= N || number - 1 - j < 0)
						break;
					if(switches[number - 1 - j] == switches[number - 1 + j]) {
						switches[number - 1 - j] = switches[number - 1 - j] == 0 ? 1 : 0;
						switches[number - 1 + j] = switches[number - 1 + j] == 0 ? 1 : 0;
					}
					else break;
				}
			}
		}
		for(int i=0; i<N; i++) {
			System.out.print(switches[i] + " ");
			if((i+1) % 20 == 0)
				System.out.println();
		}
	}
}