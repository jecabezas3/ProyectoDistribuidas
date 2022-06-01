/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.Date;

/**
 *
 * @author pilli
 */
public class detallePagosClass {
    int id_cabecera_factura;
    int codigo_forma_pago;
    int id_detalle_pagos;
    Date fecha_detalle_pagos;
    float valor_detalle_pagos;

    public int getId_cabecera_factura() {
        return id_cabecera_factura;
    }

    public void setId_cabecera_factura(int id_cabecera_factura) {
        this.id_cabecera_factura = id_cabecera_factura;
    }

    public int getCodigo_forma_pago() {
        return codigo_forma_pago;
    }

    public void setCodigo_forma_pago(int codigo_forma_pago) {
        this.codigo_forma_pago = codigo_forma_pago;
    }

    public int getId_detalle_pagos() {
        return id_detalle_pagos;
    }

    public void setId_detalle_pagos(int id_detalle_pagos) {
        this.id_detalle_pagos = id_detalle_pagos;
    }

    public Date getFecha_detalle_pagos() {
        return fecha_detalle_pagos;
    }

    public void setFecha_detalle_pagos(Date fecha_detalle_pagos) {
        this.fecha_detalle_pagos = fecha_detalle_pagos;
    }

    public float getValor_detalle_pagos() {
        return valor_detalle_pagos;
    }

    public void setValor_detalle_pagos(float valor_detalle_pagos) {
        this.valor_detalle_pagos = valor_detalle_pagos;
    }
    
    
    
    
    
}
