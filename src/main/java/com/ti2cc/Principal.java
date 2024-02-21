package com.ti2cc;

import java.util.List;
import java.util.*;

public class Principal {
	
	public static void main(String[] args) throws Exception{
		Scanner scanf = new Scanner(System.in);
		int opcao = 0;
		boolean continuar = true;
		while(continuar) { 
		System.out.println("Cadastro de Carros");
		System.out.println("Escolha uma opção a seguir para prosseguir");
		System.out.printf(" 1)Listar \n\n 2)Inserir \n\n 3)Excluir \n\n 4)Atualizar \n\n 5)Sair \n\n");
		
		boolean ValidarEntrada = false;
		while(!ValidarEntrada) {
			try {
				opcao = scanf.nextInt();
				if(opcao >= 1 && opcao <= 5) {
					ValidarEntrada = true;
				}else {
					System.out.println("Insira uma entrada valida\n\n");
				}
			} catch(Exception e) {
				System.out.println("Digite uma entrada valida\n\n");
				scanf.nextLine();
			}
		}
		
		switch (opcao) {
		case 1:
			Listar();
			break;
		case 2:
			InserirCarro();
			
			break;
		case 3:
			Excluir();
			break;
		case 4:
			Atualizar();
			break;
		case 5:
			System.out.println("É hora de dizer tchau");
			continuar = false;
			break;
		}
	}
		
		scanf.close();
		
	}
	public static void Listar() {
		CarrosDAO carrosDAO = new CarrosDAO();
		List<Carros> carros;
		carros = carrosDAO.getOrderByCodigo();//Criar array de veiculos e printar na tela
		
		for(Carros u: carros) {
			System.out.println(u.toString());
		}
	}
	
	public static Carros InserirMod() {
			Carros carro = new Carros();//construtor
			CarrosDAO carrosDAO = new CarrosDAO();//construtor
			List<Carros> carrosL = carrosDAO.getOrderByCodigo();
			boolean Verificar = false;//variavel de verificação
			String resp;//variavel de resposta
			int respI = 0;
		
			
			Scanner scanf = new Scanner(System.in);
			
			for(Carros u: carrosL) {
				respI = u.getCodigo();
			}
			carro.setCodigo(respI+1);
			
			System.out.println("Digite a placa do carro");
			do {//Limitar o tamanho da placa do carro para 7 caracteres
				resp = scanf.nextLine();
				Verificar = Delimitador(resp, 7);
			}while(Verificar == false);
			carro.setPlaca(resp);
			
			
			System.out.println("Digite a marca do carro"); //Marca do carro
			carro.setMarca(scanf.nextLine());
			
			System.out.println("Digite o modelo do carro"); //Modelo
			carro.setModelo(scanf.nextLine());
			

			System.out.println("Digite o ano do carro"); //Ano do carro
			do {
				resp = scanf.nextLine();
				if(resp.matches("[0-9]+") == true && Delimitador(resp, 4) == true) {
					Verificar = true;
				}else {
					System.out.println("Apenas 4 numeros\n");//Tratamento
					Verificar = false;
				}
			}while(Verificar == false);
			
			carro.setAno(resp);
					
			System.out.println("Digite o preço do carro");
			carro.setPreco(scanf.nextDouble());
			
			return carro;
	}
	
	public static void InserirCarro() {
		
		Carros carro = new Carros();//construtor
		CarrosDAO carrosDAO = new CarrosDAO();//construtor
		List<Carros> carrosL = carrosDAO.getOrderByCodigo();
		boolean Verificar = false;//variavel de verificação
		String resp;//variavel de resposta
		int respI = 0;
	
		
		Scanner scanf = new Scanner(System.in);
		
		for(Carros u: carrosL) {
			respI = u.getCodigo();
		}
		carro.setCodigo(respI+1);
		
		System.out.println("Digite a placa do carro");
		do {//Limitar o tamanho da placa do carro para 7 caracteres
			resp = scanf.nextLine();
			Verificar = Delimitador(resp, 7);
		}while(Verificar == false);
		carro.setPlaca(resp);
		
		
		System.out.println("Digite a marca do carro"); //Marca do carro
		carro.setMarca(scanf.nextLine());
		
		System.out.println("Digite o modelo do carro"); //Modelo
		carro.setModelo(scanf.nextLine());
		

		System.out.println("Digite o ano do carro"); //Ano do carro
		do {
			resp = scanf.nextLine();
			if(resp.matches("[0-9]+") == true && Delimitador(resp, 4) == true) {
				Verificar = true;
			}else {
				System.out.println("Apenas 4 numeros\n");//Tratamento
				Verificar = false;
			}
		}while(Verificar == false);
		
		carro.setAno(resp);
				
		System.out.println("Digite o preço do carro");
		carro.setPreco(scanf.nextDouble());
		
		if(carrosDAO.insert(carro) == true) {
			System.out.println("Inserido com sucesso");
		}
		
		//fechar Scanner
		
	}
	
	public static void Excluir() {
		CarrosDAO carrosDAO = new CarrosDAO();
		Scanner scanf = new Scanner(System.in);
		int codigo = 0;
		Listar();
		System.out.println("Digite o codigo que deseja excluir");
		codigo = scanf.nextInt();
		carrosDAO.delete(codigo);
		
		System.out.println("Excluido com sucesso");
		
	}
	
	public static void Atualizar() {
		Carros carro = new Carros();
		List<Carros> carrosL;
		CarrosDAO carrosDAO = new CarrosDAO();
		carrosL = carrosDAO.getOrderByCodigo();
		Scanner scanf = new Scanner(System.in);
		int opcao = 0;
		boolean ValidarEntrada = false;
		
		Listar();
		System.out.println("Digite o ID do carro que deseja Editar");
		opcao = scanf.nextInt();
		
		for(Carros u: carrosL) {
			if(u.getCodigo() == opcao) {
				ValidarEntrada = true;
				System.out.println("Encontrado com Sucesso");
				carro = InserirMod();
				u.setPlaca(carro.getPlaca());
				u.setMarca(carro.getMarca());
				u.setModelo(carro.getModelo());
				u.setAno(carro.getAno());
				u.setPreco(carro.getPreco());
				carrosDAO.update(u);
			}
		}
		
		if(ValidarEntrada == false) {
			System.out.println("Objeto não encontrado favor pesquisar novamente");
		}
			
		
	}
	
	public static boolean Delimitador(String str, int tam) {//Delimitar o tamanho das strings
		if(str.length() != tam) {
			System.out.println("Apenas " + tam + " caracteres");
		}else {
			return true;
		}
		return false;
	}
}
