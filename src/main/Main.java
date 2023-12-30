package main;

import java.util.Scanner;

public class Main {

	private final static int WALL = 1, EMPTY = 0, PLAYER = 5, TARGET = 9, BLOCK = 4;
	private static int dest;
	private static int destBloc;
	private static int player[] = new int[2];
	private static int bloc1[] = new int[2];
	private static int bloc2[] = new int[2];
	private static int bloc[][] = new int[10][2];
	private static int targetReached;
	private static boolean targetPassed;
	private static boolean bloc1Used;
	private static boolean bloc2Used;
	
	public static void main(String[] args) {

		char ch = 0;
		dest = 0;
		destBloc = 0;
		int map[][] = { {1,1,1,1,1,1,1,1,1,1,1},
						{1,0,0,0,0,0,0,0,0,0,1},
						{1,0,5,0,0,0,0,0,1,0,1},
						{1,0,0,0,0,4,0,0,1,0,1},
						{1,0,0,0,0,0,1,0,1,0,1},
						{1,0,0,0,4,0,1,0,1,0,1},
						{1,0,9,0,0,0,1,9,1,0,1},
						{1,0,0,0,0,0,1,1,1,0,1},
						{1,1,1,1,1,1,1,1,1,1,1}};

		player[0] = 2;
		player[1] = 2;
		bloc[0][0] = 3;
		bloc[0][1] = 5;
		bloc[1][0] = 5;
		bloc[1][1] = 4;
		bloc1[0] = 3;
		bloc1[1] = 5;
		bloc2[0] = 5;
		bloc2[1] = 4;
		targetPassed = false;
		targetReached = 0;
		bloc1Used = false;
		bloc2Used = false;

		while (targetReached < 2) {
			afficher(map);
			System.out.println(
					"\nEntrez une touche pour déplacer le perso :\nz pour monter\nq pour aller à gauche\ns pour descendre\nd pour aller à droite\n>");
			String str = new Scanner(System.in).nextLine();
			ch = str.charAt(0);
			deplacer(map, ch);
		}

	}

	public static void afficher(int map[][]) {
		for (int t[] : map) {
			for (int a : t) {
				switch (a) {
				case WALL:
					System.out.print("#");
					break;
				case EMPTY:
					System.out.print(" ");
					break;
				case PLAYER:
					System.out.print("J");
					break;
				case TARGET:
					System.out.print("X");
					break;
				case BLOCK:
					System.out.print("B");
					break;
				default:
					System.out.print(a);
					break;
				}
			}
			System.out.println();
		}
	}

	public static void deplacer(int map[][], char ch) {
		switch (ch) {
		case 'z':
			dest = map[player[0] - 1][player[1]];
			switch (dest) {
			case WALL:
				System.err.println("\nImpossible de se déplacer, il y a un mur !\n");
				break;
			case EMPTY:
				if(targetPassed == false){
					player[0] -= 1;
					map[player[0]][player[1]] = PLAYER;
					map[player[0] + 1][player[1]] = EMPTY;
				}else{
					player[0] -= 1;
					map[player[0]][player[1]] = PLAYER;
					map[player[0] + 1][player[1]] = TARGET;
					targetPassed = false;
				}
				break;
			case BLOCK:
				if(dest == map[bloc1[0]][bloc1[1]]){
					bloc1Used = true;
					bloc2Used = false;
					deplacerBloc(map,bloc1, ch);
					if(destBloc != WALL && destBloc != BLOCK){
						player[0] -= 1;
						map[player[0]][player[1]] = PLAYER;
						map[player[0] + 1][player[1]] = EMPTY;
					}
				}
				else if(dest == map[bloc2[0]][bloc2[1]]){
					bloc1Used = false;
					bloc2Used = true;
					deplacerBloc(map,bloc2, ch);
					if(destBloc != WALL && destBloc != BLOCK){
						player[0] -= 1;
						map[player[0]][player[1]] = PLAYER;
						map[player[0] + 1][player[1]] = EMPTY;
					}
				}
				break;
			case TARGET:
				player[0] -= 1;
				map[player[0]][player[1]] = PLAYER;
				map[player[0] + 1][player[1]] = EMPTY;
				targetPassed = true;
				break;
			}
			break;
		case 'q':
			dest = map[player[0]][player[1] - 1];
			switch (dest) {
			case WALL:
				System.err.println("\nImpossible de se déplacer, il y a un mur !\n");
				break;
			case EMPTY:
				if(targetPassed == false){
					player[1] -= 1;
					map[player[0]][player[1]] = PLAYER;
					map[player[0]][player[1] + 1] = EMPTY;
				}else{
					player[1] -= 1;
					map[player[0]][player[1]] = PLAYER;
					map[player[0]][player[1] + 1] = TARGET;
					targetPassed = false;
				}
				break;
			case BLOCK:
				if(dest == map[bloc1[0]][bloc1[1]]){
					bloc1Used = true;
					bloc2Used = false;
					deplacerBloc(map,bloc1 ,ch);
					if(destBloc != WALL && destBloc != BLOCK){
						player[1] -= 1;
						map[player[0]][player[1]] = PLAYER;
						map[player[0]][player[1] + 1] = EMPTY;
					}
				}
				else if(dest == map[bloc2[0]][bloc2[1]]){
					bloc1Used = false;
					bloc2Used = true;
					deplacerBloc(map,bloc2,ch);
					if(destBloc != WALL && destBloc != BLOCK){
						player[1] -= 1;
						map[player[0]][player[1]] = PLAYER;
						map[player[0]][player[1] + 1] = EMPTY;
					}
				}
				
				break;
			case TARGET:
				player[1] -= 1;
				map[player[0]][player[1]] = PLAYER;
				map[player[0]][player[1] + 1] = EMPTY;
				targetPassed = true;
				break;
			}
			break;
		case 's':
			dest = map[player[0] + 1][player[1]];
			switch (dest) {
			case WALL:
				System.err.println("\nImpossible de se déplacer, il y a un mur !\n");
				break;
			case EMPTY:
				if(targetPassed == false){
					player[0] += 1;
					map[player[0]][player[1]] = PLAYER;
					map[player[0] - 1][player[1]] = EMPTY;
				}else{
					player[0] += 1;
					map[player[0]][player[1]] = PLAYER;
					map[player[0] - 1][player[1]] = TARGET;
					targetPassed = false;
				}
				break;
			case BLOCK:
				if(dest == map[bloc1[0]][bloc1[1]]){
					bloc1Used = true;
					bloc2Used = false;
					deplacerBloc(map,bloc1, ch);
					if(destBloc != WALL && destBloc != BLOCK){
						player[0] += 1;
						map[player[0]][player[1]] = PLAYER;
						map[player[0] - 1][player[1]] = EMPTY;
					}
				}
				else if(dest == map[bloc2[0]][bloc2[1]]){
					bloc1Used = false;
					bloc2Used = true;
					deplacerBloc(map,bloc2, ch);
					if(destBloc != WALL && destBloc != BLOCK){
						player[0] += 1;
						map[player[0]][player[1]] = PLAYER;
						map[player[0] - 1][player[1]] = EMPTY;
					}
				}
				break;
			case TARGET:
				player[0] += 1;
				map[player[0]][player[1]] = PLAYER;
				map[player[0] - 1][player[1]] = EMPTY;
				targetPassed = true;
				break;
			}
			break;
		case 'd':
			dest = map[player[0]][player[1] + 1];
			switch (dest) {
			case WALL:
				System.err.println("\nImpossible de se déplacer, il y a un mur !\n");
				break;
			case EMPTY:
				if(targetPassed == false){
					player[1] += 1;
					map[player[0]][player[1]] = PLAYER;
					map[player[0]][player[1] - 1] = EMPTY;
				}else{
					player[1] += 1;
					map[player[0]][player[1]] = PLAYER;
					map[player[0]][player[1] - 1] = TARGET;
					targetPassed = false;
				}
				break;
			case BLOCK:
				if(dest == map[bloc1[0]][bloc1[1]]){
					bloc1Used = true;
					bloc2Used = false;
					deplacerBloc(map,bloc1, ch);
					if(destBloc != WALL && destBloc != BLOCK){
						player[1] += 1;
						map[player[0]][player[1]] = PLAYER;
						map[player[0]][player[1] - 1] = EMPTY;
					}
				}
				else if(dest == map[bloc2[0]][bloc2[1]]){
					bloc1Used = false;
					bloc2Used = true;
					deplacerBloc(map,bloc2, ch);
					if(destBloc != WALL && destBloc != BLOCK){
						player[1] += 1;
						map[player[0]][player[1]] = PLAYER;
						map[player[0]][player[1] - 1] = EMPTY;
					}
				}
				break;
			case TARGET:
				player[1] += 1;
				map[player[0]][player[1]] = PLAYER;
				map[player[0]][player[1] - 1] = EMPTY;
				targetPassed = true;
				break;
			}
			break;
		default:
			System.out.println("Caractère invalide");
			break;
		}
	}

	public static void deplacerBloc(int[][] map, int bloc[] ,char ch) {
		switch (ch) {
		case 'z':
			destBloc = map[bloc[0] - 1][bloc[1]];
			switch (destBloc) {
			case WALL:
				System.err.println("\nImpossible de se déplacer, il y a un mur !\n");
				break;
			case EMPTY:
				bloc[0] -= 1;
				map[bloc[0]][bloc[1]] = BLOCK;
				if(bloc1Used){
					bloc1 = bloc;
				}else if(bloc2Used){
					bloc2 = bloc;
				}
				break;
			 case TARGET: 
				if(targetReached == 1){
					System.out.println("Bravo vous avez gagné !");
			     	bloc[0] -= 1;
					map[bloc[0]][bloc[1]] = BLOCK;
					map[bloc[0] + 1][bloc[1]] = PLAYER;
					map[bloc[0] + 2][bloc[1]] = EMPTY;
					targetReached++;
					afficher(map);
					if(bloc1Used){
						bloc1 = bloc;
					}else if(bloc2Used){
						bloc2 = bloc;
					}
				}
				else{
					bloc[0] -= 1;
					map[bloc[0]][bloc[1]] = BLOCK;
					map[bloc[0] + 1][bloc[1]] = PLAYER;
					map[bloc[0] + 2][bloc[1]] = EMPTY;
					targetReached++;
					if(bloc1Used){
						bloc1 = bloc;
					}else if(bloc2Used){
						bloc2 = bloc;
					}
				 }
			 break;
			 
			}
			break;
		case 'q':
			destBloc = map[bloc[0]][bloc[1] - 1];
			switch (destBloc) {
			case WALL:
				System.err.println("\nImpossible de se déplacer, il y a un mur !\n");
				break;
			case EMPTY:
				bloc[1] -= 1;
				map[bloc[0]][bloc[1]] = BLOCK;
				if(bloc1Used){
					bloc1 = bloc;
				}else if(bloc2Used){
					bloc2 = bloc;
				}
				break;
			 case TARGET: 
				 if(targetReached == 1){
			     	 System.out.println("Bravo vous avez gagné !");
					 bloc[1] -= 1;
					 map[bloc[0]][bloc[1]] = BLOCK;
					 map[bloc[0]][bloc[1] + 1] = PLAYER;
					 map[bloc[0]][bloc[1] + 2] = EMPTY;
					 afficher(map); 
					 targetReached++;
					 if(bloc1Used){
							bloc1 = bloc;
						}else if(bloc2Used){
							bloc2 = bloc;
						}
				 }
				 else{
					 bloc[1] -= 1;
					 map[bloc[0]][bloc[1]] = BLOCK;
					 map[bloc[0]][bloc[1] + 1] = PLAYER;
					 map[bloc[0]][bloc[1] + 2] = EMPTY;
					 targetReached++;
					 if(bloc1Used){
							bloc1 = bloc;
						}else if(bloc2Used){
							bloc2 = bloc;
						}
				 }
			 break;
			 
			}
			break;
		case 's':
			destBloc = map[bloc[0] + 1][bloc[1]];
			switch (destBloc) {
			case WALL:
				System.err.println("\nImpossible de se déplacer, il y a un mur !\n");
				break;
			case EMPTY:
				bloc[0] += 1;
				map[bloc[0]][bloc[1]] = BLOCK;
				if(bloc1Used){
					bloc1 = bloc;
				}else if(bloc2Used){
					bloc2 = bloc;
				}
				break;
			 case TARGET:
				if(targetReached == 1){ 
					System.out.println("Bravo vous avez gagné !");
					bloc[0] += 1;
					map[bloc[0]][bloc[1]] = BLOCK;
					map[bloc[0] - 1][bloc[1]] = PLAYER;
					map[bloc[0] - 2][bloc[1]] = EMPTY;
					afficher(map);
					targetReached++;
					if(bloc1Used){
						bloc1 = bloc;
					}else if(bloc2Used){
						bloc2 = bloc;
					}
				}
				else{
					bloc[0] += 1;
					map[bloc[0]][bloc[1]] = BLOCK;
					map[bloc[0] - 1][bloc[1]] = PLAYER;
					map[bloc[0] - 2][bloc[1]] = EMPTY;
					targetReached++;
					if(bloc1Used){
						bloc1 = bloc;
					}else if(bloc2Used){
						bloc2 = bloc;
					}
				}
			 break;
			}
			break;
		case 'd':
			destBloc = map[bloc[0]][bloc[1] + 1];
			switch (destBloc) {
			case WALL:
				System.err.println("\nImpossible de se déplacer, il y a un mur !\n");
				break;
			case EMPTY:
				bloc[1] += 1;
				map[bloc[0]][bloc[1]] = BLOCK;
				if(bloc1Used){
					bloc1 = bloc;
				}else if(bloc2Used){
					bloc2 = bloc;
				}
				break;
			case TARGET:
				if(targetReached == 1){ 
					System.out.println("Bravo vous avez gagné !");
					bloc[1] += 1;
					map[bloc[0]][bloc[1]] = BLOCK;
					map[bloc[0]][bloc[1] - 1] = PLAYER;
					map[bloc[0]][bloc[1] - 2] = EMPTY;
					afficher(map);
					targetReached++;
					if(bloc1Used){
						bloc1 = bloc;
					}else if(bloc2Used){
						bloc2 = bloc;
					}
				}
				else{
					bloc[1] += 1;
					map[bloc[0]][bloc[1]] = BLOCK;
					map[bloc[0]][bloc[1] - 2] = EMPTY;
					targetReached++;
					if(bloc1Used){
						bloc1 = bloc;
					}else if(bloc2Used){
						bloc2 = bloc;
					}
				}
			break;
			}
		}
	}
}
