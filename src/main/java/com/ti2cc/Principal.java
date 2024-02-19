package com.ti2cc;

import java.util.List;


public class Principal {
	
	public static void main(String[] args) throws Exception{
		CarrosDAO carrosDAO = new CarrosDAO();
		
		System.out.println("Inserir Usuario");
		Carros Carros = new Carros(5, "123mu234", "mustang", "ford", "2023", 150000.90);
		if(carrosDAO.insert(Carros) == true) {
			System.out.println("Feito com sucesso" + Carros.toString());
		}
	}
}
