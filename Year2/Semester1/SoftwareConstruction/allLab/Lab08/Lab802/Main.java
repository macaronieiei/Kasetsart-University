package Lab08.Lab802;

import java.util.Iterator;

public class Main {
	public static void main(String[] args) {
		Containers namesRepository = new Containers() {
			String[] names = { "John", "May", "Ryan" };
			public Iterator getIterator() {
				return new Iterator() {
					int index = 0;
					public boolean hasNext() {
						if (index < names.length)
							return true;
						return false;
					}

					public Object next() {
						return names[index++];
					}

					public void remove() {
					}
				};
			}
		};
		for (Iterator iter = namesRepository.getIterator(); iter.hasNext();){
			String name = (String) iter.next();
			System.out.println("Name : " + name);
		}
	} 
}