package thread;

/**
 * @author wcl
 */
public class Lambda {
	/**
	 * ②：静态内部类
	 */
	static class Like2 implements Like {

		@Override
		public void lambda() {
			System.out.println("②：静态内部类==》i like lambda");
		}
	}

	public static void main(String[] args) {
		Like like = new Like1();
		like.lambda();

		like = new Like2();
		like.lambda();

		// ③：局部内部类
		class Like3 implements Like {

			@Override
			public void lambda() {
				System.out.println("③：局部内部类==》i like lambda");
			}
		}

		like = new Like3();
		like.lambda();

		// ④：匿名内部类
		like = new Like() {
			@Override
			public void lambda() {
				System.out.println("④：匿名内部类==》i like lambda");
			}
		};
		like.lambda();

		// ⑤：lambda表达式
		like = () -> System.out.println("⑤：lambda表达式==》i like lambda");
		like.lambda();
	}
}

/**
 * 定义一个函数式接口
 */
interface Like {
	/**
	 * 打印
	 */
	void lambda();
}

/**
 * ①：实现类
 */
class Like1 implements Like {

	@Override
	public void lambda() {
		System.out.println("①：实现类==》i like lambda");
	}
}

