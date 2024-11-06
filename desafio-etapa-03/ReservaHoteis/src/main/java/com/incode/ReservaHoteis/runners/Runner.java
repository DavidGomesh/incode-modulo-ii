package com.incode.ReservaHoteis.runners;

import static com.incode.ReservaHoteis.model.enumarate.StatusReserva.ATIVA;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.incode.ReservaHoteis.model.Hotel;
import com.incode.ReservaHoteis.model.Quarto;
import com.incode.ReservaHoteis.model.Reserva;
import com.incode.ReservaHoteis.model.enumarate.StatusQuarto;
import com.incode.ReservaHoteis.repository.HotelRepository;
import com.incode.ReservaHoteis.repository.QuartoRepository;
import com.incode.ReservaHoteis.repository.ReservaRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class Runner implements CommandLineRunner {

    private final Scanner scanner;

    private final HotelRepository hotelRepository;
    private final QuartoRepository quartoRepository;
    private final ReservaRepository reservaRepository;

    @Override
    public void run(String... args) throws Exception {
        var opcao = "";
        do {
            limparTela();
            opcao = exibirMenuPrincipal();
            limparTela();

            switch (opcao) {
                case "1": cadastrarHotel();   break;
                case "2": cadastrarQuarto();  break;
                case "3": cadastrarReserva(); break;

                case "4": finalizarReserva(); break;

                case "5": listarHoteis();     break;
                case "6": listarQuartos();    break;
                case "7": listarReservas();   break;

                case "0": System.out.println("Aplicacao encerrada!"); break;
                default : System.out.println("Opcao invalida!"); break;
            }

            pausarPrograma();

        } while (!opcao.equals("0"));

        scanner.close();
    }



    // --------------------------------------------------------
    // MENU PRINCIPAL
    private String exibirMenuPrincipal() {
        System.out.println("========== RESERVA DE HOTEIS ==========");
        System.out.println("1 - Cadastrar Hotel");
        System.out.println("2 - Cadastrar Quarto");
        System.out.println("3 - Cadastrar Reserva");
        System.out.println("4 - Finalizar Reserva");
        System.out.println("5 - Listar Hoteis");
        System.out.println("6 - Listar Quartos");
        System.out.println("7 - Listar Reservas");
        System.out.println("0 - Sair");

        return soliciarOpcao();
    }



    // --------------------------------------------------------
    // CADASTRO DE HOTEIS
    @Transactional
    private void cadastrarHotel() {
        System.out.println("========== CADASTRAR HOTEL ==========");
        var nome = solicitarNomeHotel();

        try {
            hotelRepository.save(
                Hotel.builder()
                .nome(nome)
                .build()
            );

            System.out.println("\n\n\nHotel cadastrado com sucesso!\n\n\n");

        } catch (Exception e) {
            System.out.println("\n\n\nFalha ao cadastrar o Hotel!\n\n\n");
        }
    }

    private String solicitarNomeHotel() {
        try {
            System.out.print("Nome: ");
            var nome = scanner.nextLine();

            if (nome.isBlank()) {
                throw new RuntimeException("O nome do Hotel nao pode estar em branco!");
            }

            return nome;

        } catch (Exception e) {
            return solicitarNomeHotel();
        }
    }



    // --------------------------------------------------------
    // CADASTRO DE QUARTOS
    @Transactional
    private void cadastrarQuarto() {
        System.out.println("========== CADASTRAR QUARTO ==========");

        if (hotelRepository.count() == 0) {
            System.out.println("\n\n\nNao ha hoteis cadastrados!\n\n\n");
            return;
        }

        System.out.println("\nSelecione um hotel: ");
        exibirHoteisCadastrados();

        var hotel = selecionarHotel();

        System.out.println();
        exibirHotelEQuartos(hotel);

        var numQuarto = solicitarNumeroQuarto(hotel);
        var descQuarto = solicitarDescricaoQuarto();

        try {
            quartoRepository.saveAndFlush(Quarto.builder()
                .numero(numQuarto)
                .descricao(descQuarto)
                .hotel(hotel)
                .build()
            );

            System.out.println("\n\n\nQuarto cadastrado com sucesso!\n\n\n");

        } catch (Exception e) {
            System.out.println("\n\n\nFalha ao cadastrar o Quarto!\n\n\n");
        }
    }

    private Hotel selecionarHotel() {
        try {
            return hotelRepository.findById(solicitarId()).orElseThrow();

        } catch (Exception ex) {
            System.out.println("Hotel invalido!\n");
            return selecionarHotel();
        }
    }

    private String solicitarNumeroQuarto(Hotel hotel) {
        var numQuarto = solicitarString("\nNumero do quarto: ", "Numero invalido!");

        var optQuarto = quartoRepository.findByHotelAndNumero(hotel, numQuarto);
        if (optQuarto.isPresent()) {
            System.out.println("Um quarto com esse numero ja existe para esse hotel!");
            return solicitarNumeroQuarto(hotel);
        }

        return numQuarto;
    }

    private String solicitarDescricaoQuarto() {
        return solicitarString("Descricao do quarto: ", "Descricao invalida!");
    }



    // --------------------------------------------------------
    // CADASTRO DE RESERVA
    @Transactional
    private void cadastrarReserva() {
        System.out.println("========== CADASTRAR RESERVA ==========");

        if (quartoRepository.count() == 0) {
            System.out.println("\n\n\nNao ha quartos cadastrados!\n\n\n");
            return;
        }

        System.out.println("\nSelecione um hotel: ");
        exibirHoteisCadastrados();
        var hotel = selecionarHotel();

        var quartos = quartoRepository.findByHotelAndStatus(hotel, StatusQuarto.DISPONIVEL);
        if (quartos.isEmpty()) {
            System.out.println("\n\n\nNao ha quartos disponiveis!\n\n\n");
            return;
        }

        System.out.println("\nSelecione um quarto: ");
        exibirQuartosDisponiveisDeHotel(hotel);
        var quarto = selecionarQuartoDisponivel(hotel);

        var dataInicio = informarDataInicio();
        var qtdDias = informarQtdDias();

        quarto.reservar();

        try {
            quartoRepository.save(quarto);
            reservaRepository.save(Reserva.builder()
                .quarto(quarto)
                .dataInicio(dataInicio)
                .dataPrevistaFim(dataInicio.plusDays(qtdDias))
                .build()
            );

            System.out.println("\n\n\nReserva cadastrada com sucesso!\n\n\n");

        } catch (Exception e) {
            System.out.println("\n\n\nFalha ao cadastrar a Reserva!\n\n\n");
        }
    }

    public void exibirQuartosDisponiveisDeHotel(Hotel hotel) {
        System.out.println("HOTEL: " + hotel.getNome());
        hotel.getQuartos().stream()
            .filter(Quarto::isDisponivel)
            .forEach(this::exibirQuarto)
        ;
    }

    private Quarto selecionarQuartoDisponivel(Hotel hotel) {
        try {
            return quartoRepository.findByHotelAndNumero(hotel, selecionarQuarto()).orElseThrow();

        } catch (Exception ex) {
            System.out.println("Quarto invalido!\n");
            return selecionarQuartoDisponivel(hotel);
        }
    }

    private String selecionarQuarto() {
        return solicitarString("\nNumero do quarto: ", "Numero invalido!");
    }

    private LocalDate informarDataInicio() {
        try {
            System.out.print("Informe a data de inicio no formato 'dd/mm/aa': ");
            var data = scanner.nextLine().trim();
            return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yy"));

        } catch (Exception e) {
            System.out.println("Data invalida!\n");
            return informarDataInicio();
        }
    }

    private Integer informarQtdDias() {
        try {
            System.out.print("Informe a quantidade de dias: ");
            var qtdDias = Integer.parseInt(scanner.nextLine().trim());

            if (qtdDias < 1) {
                throw new RuntimeException("Quantidade de dias nao pode ser menor que 1");
            }

            return qtdDias;

        } catch (Exception e) {
            System.out.println("Quantidade de dias invalida!\n");
            return informarQtdDias();
        }
    }



    // --------------------------------------------------------
    // FINALIZAR RESERVA
    private void finalizarReserva() {
        System.out.println("========== FINALIZAR RESERVA ==========");

        var reservas = reservaRepository.findAllByStatus(ATIVA);
        if (reservas.isEmpty()) {
            System.out.println("\n\n\nNao ha reservas ativas!\n\n\n");
            return;
        }

        System.out.println("\nSelecione a reserva: ");
        reservas.forEach(this::exibirReserva);

        var reserva = selecionarReserva(reservas);
        var dataFim = informarDataFim(reserva.getDataInicio());

        reserva.finalizar(dataFim);
        reserva.getQuarto().liberar();

        try {
            quartoRepository.save(reserva.getQuarto());
            reservaRepository.save(reserva);

            System.out.println("\n\n\nReserva finalizada com sucesso!\n\n\n");

        } catch (Exception e) {
            System.out.println("\n\n\nFalha ao finalizar a Reserva!\n\n\n");
        }
    }

    private Reserva selecionarReserva(List<Reserva> reservas) {
        try {
            var idReserva = solicitarId();
            var reserva = reservas.stream().filter(r -> r.getIdReserva().equals(idReserva)).toList();

            if (reserva.isEmpty()) {
                throw new RuntimeException("Reserva invalida");
            }

            return reserva.getFirst();

        } catch (Exception e) {
            System.out.println("Reserva invalida!\n");
            return selecionarReserva(reservas);
        }

    }

    private LocalDate informarDataFim(LocalDate dataInicio) {
        try {
            System.out.print("Informe a data de fim no formato 'dd/mm/aa': ");
            var dataStr = scanner.nextLine().trim();
            var dataFim = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yy"));

            if (dataFim.isBefore(dataInicio)) {
                throw new RuntimeException("Data de fim antes da data de inicio");
            }

            return dataFim;

        } catch (Exception e) {
            System.out.println("Data invalida!\n");
            return informarDataInicio();
        }
    }


    // --------------------------------------------------------
    // LISTAR HOTEIS
    private void listarHoteis() {
        System.out.println("========== LISTAR HOTEIS ==========");
        exibirHoteisCadastrados();
    }

    private void exibirHoteisCadastrados() {
        var hoteis = hotelRepository.findAll();

        if (hoteis.isEmpty()) {
            System.out.println("\n\n\nNao ha hoteis cadastrados!\n\n\n");
            return;
        }

        hoteis.forEach(this::exibirHotel);
    }

    private void exibirHotel(Hotel hotel) {
        System.out.println(hotel.getIdHotel() + " - " + hotel.getNome());
    }



    // --------------------------------------------------------
    // LISTAR QUARTOS
    private void listarQuartos() {
        System.out.println("========== LISTAR QUARTOS ==========");
        exibirQuartosCadastrados();
        System.out.println();
    }

    private void exibirQuartosCadastrados() {
        var hoteis = hotelRepository.findAll();

        if (hoteis.isEmpty()) {
            System.out.println("\n\n\nNao ha quartos cadastrados!\n\n\n");
            return;
        }

        hoteis.forEach(hotel -> {
            exibirHotelEQuartos(hotel);
            exbirSeparador();
        });
    }

    private void exibirHotelEQuartos(Hotel hotel) {
        System.out.println("HOTEL: " + hotel.getNome());

        var quartos = hotel.getQuartos();

        if (quartos.isEmpty()) {
            System.out.println("Nao ha quartos cadastrados!");
            return;
        }

        quartos.forEach(this::exibirQuarto);
    }

    private void exibirQuarto(Quarto quarto) {
        System.out.println(
            "(Nº " + quarto.getNumero() + ") " +
            quarto.getDescricao() + " (" + quarto.getStatus() + ")"
        );
    }



    // --------------------------------------------------------
    // LISTAR RESERVAS
    private void listarReservas() {
        System.out.println("========== LISTAR RESERVAS ==========");
        exibirReservasCadastradas();
    }

    private void exibirReservasCadastradas() {
        var reservas = reservaRepository.findAll();

        if (reservas.isEmpty()) {
            System.out.println("\n\n\nNao ha reservas cadastradas!\n\n\n");
        }

        reservas.forEach(this::exibirReserva);
    }

    private void exibirReserva(Reserva reserva) {
        var dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");

        var quarto = reserva.getQuarto();
        var hotel = quarto.getHotel();
        var dataFim = reserva.getDataFim();

        System.out.println(
            "ID Reserva: " + reserva.getIdReserva() +
            " (" + reserva.getStatus() + ")"
        );

        System.out.println(
            hotel.getNome() +
            " | (" + quarto.getNumero() + ") " + quarto.getDescricao()
        );

        System.out.println(
            "Inicio: " + reserva.getDataInicio().format(dateFormatter) +
            " | Prev. Fim: " + reserva.getDataPrevistaFim().format(dateFormatter) +
            " | Fim: " + (dataFim != null ? dataFim.format(dateFormatter) : "-")
        );

        exbirSeparador();
    }



    // --------------------------------------------------------
    // FUNCOES AUXILIARES
    private String soliciarOpcao() {
        System.out.print("Opcao: ");
        return scanner.nextLine();
    }

    private Integer solicitarId() {
        System.out.print("Informe o ID: ");
        var id = scanner.nextLine();

        try {
            return Integer.parseInt(id);

        } catch (Exception e) {
            System.out.println("ID invalido!\n");
            return solicitarId();
        }
    }

    private String solicitarString(String mensagem, String mensagemErro) {
        try {
            System.out.print(mensagem);
            var str = scanner.nextLine().trim();

            if (str.isBlank()) {
                throw new RuntimeException("String vazia");
            }

            return str;

        } catch (Exception e) {
            System.out.println(mensagemErro);
            return solicitarString(mensagem, mensagemErro);
        }
    }

    private void pausarPrograma() {
        System.out.println("Pressione enter para continuar...");
        scanner.nextLine();
    }

    private void limparTela() {
        for (int i=0; i<100; i++) {
            System.out.println();
        }
    }

    private void exbirSeparador() {
        System.out.println("-----------------------------------");
    }
}
