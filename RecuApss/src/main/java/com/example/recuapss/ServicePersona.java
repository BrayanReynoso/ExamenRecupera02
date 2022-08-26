package com.example.recuapss;

import com.example.recuapss.model.BeanPersona;
import com.example.recuapss.model.DaoPersona;
import com.example.recuapss.utils.ResultAction;


import java.util.List;

public class ServicePersona {
    DaoPersona daoPersona = new DaoPersona();
    public List<BeanPersona> findAll(){return daoPersona.findAll();}

    public ResultAction save(BeanPersona persona){
        ResultAction result = new ResultAction();
        if(daoPersona.add(persona)){
            result.setResult(true);
            result.setMessage("Persona registrado correctamente");
            result.setStatus(200);
        }else{
            result.setResult(false);
            result.setMessage("Ocurrio un error al registrar");
            result.setStatus(400);
        }
        return result;
    }
    public BeanPersona getPersona(int id){return daoPersona.findOne(id);}
    public ResultAction update (BeanPersona persona){
        ResultAction result = new ResultAction();
        if(daoPersona.update(persona)){
            System.out.println(persona.getNombre());
            System.out.println(persona.getApellidos());
            System.out.println(persona.getBirthday());
            result.setStatus(200);
            result.setResult(false);
            result.setMessage("Persona actualizada correctamente");
        }else{
            result.setStatus(400);
            result.setResult(true);
            result.setMessage("Ocurrio un error");
        }
        return result;
    }
    public ResultAction delete(String id){
        ResultAction result = new ResultAction();
        try {
            if (daoPersona.delete(Integer.parseInt(id))){
                result.setStatus(200);
                result.setResult(false);
                result.setMessage("Usuario eliminado correctamente");
            }else{
                result.setStatus(400);
                result.setResult(true);
                result.setMessage("Ocurrio un error");
            }
        }catch (NumberFormatException e){
            result.setStatus(400);
            result.setResult(true);
            result.setMessage("Ocurrio un error");
        }
        return result;
    }
}
