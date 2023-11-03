package model.builder;


import model.Campeonato;
import model.Time;

import java.util.ArrayList;
import java.util.List;

public class CampeonatoBuilder {

    private final List<Time> times = new ArrayList<>();
    private String campeao;
    private String viceCampeao;


    public CampeonatoBuilder setCampeao(String camepao) {
        this.campeao = campeao;
        return this;
    }


    public CampeonatoBuilder setViceCampeao(String viceCampeao) {
        this.viceCampeao = viceCampeao;
        return this;
    }


    public Campeonato create(){
        return new Campeonato(times, campeao, viceCampeao);
    }

    public CampeonatoBuilder addTime(Time time){
        if(exist(time)){
             throw new IllegalArgumentException("Time Já existente.");
        }else{
            this.times.add(time);
        }
        return this;
    }


    public boolean exist(Time time){
        return times.contains(time);
    }



}
