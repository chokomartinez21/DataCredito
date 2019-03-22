/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.datacredito;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexander Martinez
 */
public class Inicio {

    List<Persona> listaPersona;
    List<Reporte> listaReporte;

    public List<Persona> leerArchivo(String archivo) throws Exception {
        List<Persona> listaPersona = new ArrayList<>();
        //List<Reporte> listaReporte= new ArrayList<>();
        String cadena;
        FileReader f;
        BufferedReader b;
        try {
            f = new FileReader(archivo);
            b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                List<Reporte> listaReporte = new ArrayList<>();
                String[] personaVector = cadena.split(",");
                Persona persona = new Persona(Integer.parseInt(personaVector[0]), personaVector[1], personaVector[2], personaVector[3]);
                listaPersona.add(persona);
                String[] reportes = personaVector[4].split("%");
                int longitud = reportes.length;

                for (int i = 0; i < longitud; i++) {

                    String[] repor = reportes[i].split("-");
                    Reporte reporte = new Reporte(Integer.parseInt(repor[0]), repor[1], repor[2], Boolean.valueOf(repor[3]), Integer.parseInt(repor[4]));
                    listaReporte.add(reporte);
                    persona.setReporte(listaReporte);

                }

                persona.setReporte(listaReporte);
            }
            b.close();
            return listaPersona;

        } catch (FileNotFoundException ex) {
            System.err.print("No se puede encontra el archivo");
            throw new FileNotFoundException("No se puede encontra el archivo");
        } catch (IOException ex) {
            System.err.print("Error al leer el archivo");
            throw new IOException("Error al leer el archivo");
        }

    }

    public void imprimepersona(List<Persona> list) {

        try {
            listaPersona = list;
        } catch (Exception ex) {
            System.err.print("Error al leer el archivo");
        }
        for (Persona x : listaPersona) {
            System.out.println(" Nombre: " + x.getNombre() + " Apellido: " + x.getApellido() + " correo: " + x.getCorreo());
            for (Reporte r : x.getReporte()) {
                if (r.isEstado() == false) {
                    System.out.println(" codigo: " + r.getCodigo() + " Empresa: " + r.getEmpresa() + " Descricion: " + r.getDescripcion() + " Valor: " + r.getValor());
                }
            }

        }
    }

}
