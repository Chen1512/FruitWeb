package edu.ncucst.fritemmarket.web.service;



import edu.ncucst.fritemmarket.web.beans.Fruit;
import edu.ncucst.fritemmarket.web.dao.FruitDao;

import java.util.ArrayList;

public class FruitService {
    private FruitDao dao=new FruitDao();

    public ArrayList<Fruit> queryAllFruit(){
        ArrayList<Fruit> fruits=dao.queryAllFruit();
        return fruits;
    }

    public ArrayList<Fruit> queryFruitByCond(Fruit fruit){
        ArrayList<Fruit> fruits=dao.queryFruitByCond(fruit);
        return fruits;
    }

    // 排序
    public ArrayList<Fruit> sortFruits(String sort){
        ArrayList<Fruit> fruits = dao.sortFruits(sort);
        return fruits;
    }


    // 排序（降序）
    public ArrayList<Fruit> sortFruitsByDesc(String sort){
        ArrayList<Fruit> fruits = dao.sortFruitsByDesc(sort);
        return fruits;
    }

    public boolean addFruit(Fruit fruit){
        ArrayList<Fruit> fruits=queryAllFruit();
        for(Fruit data:fruits){
            if(fruit.getNumber().equals(data.getNumber()))
                return false;
        }
        dao.addFruit(fruit);
        return true;
    }

    public boolean updateFruit(Fruit fruit){
        ArrayList<Fruit> fruits=queryAllFruit();
        for(Fruit data:fruits){
            if(fruit.getNumber().equals(data.getNumber())){
                dao.updateFruit(fruit);
                return true;
            }
        }
        return false;
    }

    public boolean delFruit(String delNumber){
        ArrayList<Fruit> fruits=queryAllFruit();
        for(Fruit data:fruits){
            if(delNumber.equals(data.getNumber())){
                dao.delFruit(delNumber);
                return true;
            }
        }
        return false;
    }
}
