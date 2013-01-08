
/**
 * Author: glsensor@gmail.com (Joe Au)
 * https://github.com/JoeAu/
 */

package com.JoeAu.JoSerializer.Test;

public enum PEnum1 {
		xxx(0),
		ccc(1),
		yyy(2);
		
		private int value = 0;

		private PEnum1(int value) {
			this.value = value;
		}
		
		public static PEnum1 valueOf(int value) {
			switch (value) {
			case 0:
				return xxx;
			case 1:
				return ccc;
			case 2:
				return yyy;
			}
			return xxx;
		}
			
		 public int value() {
			 return this.value;
		 }
	}