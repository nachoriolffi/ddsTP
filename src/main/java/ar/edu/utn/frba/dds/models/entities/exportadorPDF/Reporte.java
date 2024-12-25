package ar.edu.utn.frba.dds.models.entities.exportadorPDF;

import ar.edu.utn.frba.dds.models.converters.LocalDateConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

@Setter
@Getter
@Entity
@Table(name = "reporte")
public class Reporte {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "pathDocumento", columnDefinition = "TEXT")
    private String pathDocumento;

    @Convert(converter = LocalDateConverter.class)
    @Column(name = "fechaCreacion")
    private LocalDate fechaCreacion;
    @Transient
    private String mes;
    @Transient
    private Integer anio;
    @Transient
    private Integer semana;

    public Reporte(String pathDocumento) {
        this.pathDocumento = pathDocumento;
        this.fechaCreacion = LocalDate.now();
    }

    public void calcularSemana() {
        this.semana = this.fechaCreacion.get(WeekFields.of(Locale.getDefault()).weekOfMonth());
    }

    public void calcularMes() {
        this.mes = fechaCreacion.getMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es")).toUpperCase();
    }

    public void calcularAnio() {
        this.anio = fechaCreacion.getYear();
    }

    public Reporte() {
    }
}
