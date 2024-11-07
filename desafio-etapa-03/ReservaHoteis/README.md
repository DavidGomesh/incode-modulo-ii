# Reserva de Hoteis

## Desafio

Desenvolva um sistema para gerenciar reservas em hotéis. Você deve criar as seguintes classes: **Quarto**, **Reserva**, **Hotel** e implementar suas funcionalidades para garantir que o sistema seja funcional e permita a gestão de quartos e reservas.

## Solução

Para a conexão com o Banco de Dados **MySQL** foi usado o **JPA** ***(Java Persistence API)***. Toda a configuração foi feita usando o **Spring Boot**. Outras bibliotecas também foram utilizadas para auxiliar o desenvolvimento da aplicação, como o ***lombok***, para gerar códigos boilerplate como getters, setters e as funções para o uso do padrão Builder.

### Hotel

```java
@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHotel;

    @NotBlank
    private String nome;

    @Builder.Default
    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    private Set<Quarto> quartos = new HashSet<>();
}
```

### Quarto

```java
@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idQuarto;

    @NotBlank
    private String numero;

    @NotBlank
    private String descricao;

    @NotNull
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private StatusQuarto status = DISPONIVEL;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_hotel")
    private Hotel hotel;

    public Boolean isDisponivel() {
        return status == DISPONIVEL;
    }

    public void reservar() {
        this.status = RESERVADO;
    }

    public void liberar() {
        this.status = DISPONIVEL;
    }
}
```

Para o quarto, um enum chamado `StatusQuarto` foi criado para controlar se o quarto está disponível ou reservado.

### Reserva

```java
@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReserva;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_quarto")
    private Quarto quarto;

    @NotNull
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private StatusReserva status = ATIVA;

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private LocalDate dataPrevistaFim;

    private LocalDate dataFim;

    public void finalizar(LocalDate dataFim) {
        this.dataFim = dataFim;
        status = FINALIZADA;
    }
}
```

Para a reserva, um enum chamado `StatusReserva` foi criado para controlar se um reserva está ativa ou finalizada.

Uma reserva possui uma data de início e uma de fim previsto. A data de fim será a data que a reserva foi finalizada, e essa data não poderá ser uma data antes da data de início.



