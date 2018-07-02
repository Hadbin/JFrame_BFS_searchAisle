package BFS;


public class demo_0 {

	class B {
		public B() {
			System.out.println("B");
		}
		public void go() {
			System.out.println("go");
		}
	}
	public B show() {
		return this.new B();
	}
	public static void main(String[] args) {
		B b=new demo_0().show();
		b.go();
	}
}
