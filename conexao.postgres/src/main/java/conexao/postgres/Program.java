package conexao.postgres;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Program {
	public static void main(String[] Args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		DAO dao = new DAO();
		dao.conectar();
		
	    int id;
	    String nome, data, telefone;
		int menu_number;
		
		do {
			System.out.print("\nMenu:\n1)Listar\n2)Inserir\n3)Excluir\n4)Atualizar\n5)Sair\n");
			menu_number = Integer.parseInt(reader.readLine());
			switch (menu_number) {
				case 2:
					System.out.println("\nInsira os dados da nova pessoa a ser adicionada:");
					System.out.println("Insira o id:");
					id = Integer.parseInt(reader.readLine());
					System.out.println("Insira o nome:");
					nome = reader.readLine();
					System.out.println("Insira a data de nascimento:");
					data = reader.readLine();
					System.out.println("Insira telefone:");
					telefone = reader.readLine();
					Inserir(dao, id, nome, data, telefone);
					break;
				case 4:
					System.out.println("\nInsira os dados da pessoa a ser atualizada:");
					System.out.println("Insira o id:");
					id = Integer.parseInt(reader.readLine());
					System.out.println("Insira o nome:");
					nome = reader.readLine();
					System.out.println("Insira a data de nascimento:");
					data = reader.readLine();
					System.out.println("Insira telefone:");
					telefone = reader.readLine();
					Atualizar(dao, id, nome, data, telefone);
					break;
				case 3:
					System.out.println("\nInsira o id da pessoa a ser excluida:");
					id = Integer.parseInt(reader.readLine());
					Excluir(dao, id);
					break;
				case 1:
					System.out.println();
					Listar(dao);
					break;
                default:
                	break;
			}
		} while (menu_number != 5);
		
		
		
	}
	
	public static void Inserir(DAO dao, int id, String nome, String data, String telefone) {
		Pessoa pessoa = new Pessoa(id, nome, data, telefone);
		if(dao.inserirPessoa(pessoa) == true) {
			System.out.println("\nInserção com sucesso -> " + pessoa.toString());
		}
	}
	
	public static void Atualizar(DAO dao, int id, String nome, String data, String telefone) {
		Pessoa pessoa = new Pessoa(id, nome, data, telefone);
		if(dao.atualizarPessoa(pessoa) == true) {
			System.out.println("\nAtualização com sucesso -> " + pessoa.toString());
		}
	}
	
	public static void Excluir(DAO dao, int id) {
		dao.excluirPessoa(id);
	}
	
	public static void Listar(DAO dao) {
		Pessoa[] pessoas = dao.getPessoas();
		if (pessoas == null) {
			System.out.println("Não há pessoas cadastradas");
		} else {
			for (int i = 0; i < pessoas.length; i++) {
				System.out.println(pessoas[i].toString());
			}
		}
	}	
}
