package com.mvc.upc.service;

import com.mvc.upc.model.Address;
import com.mvc.upc.model.WareHouse;
import com.mvc.upc.repository.AddressRepository;
import com.mvc.upc.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

/**
 * Created by chenzifeng on 2017/7/11.
 */
@Service
public class LocationService {
    @Autowired
    WareHouseRepository wareHouseRepository;
    @Autowired
    AddressRepository addressRepository;

    public void setlocation(/*String locaiton*/){

        String location = "[39.908419482750716, 116.3975715637207]";

        double longitude = subLocation(location)[0];
        double latitude = subLocation(location)[1];
        System.out.println("经度："+longitude);
        System.out.println("纬度："+latitude);
        System.out.println("坐标："+location);

        String l1 = "[39.933301809920955, 116.37165069580078]";
        String l2 = "[39.90776109736608, 116.41937255859375]";
        String[] ll1 = l1.split(",|]");

    }
    /**
     *@param addressId 用户的位置id

     *@return 返回当前城市中最近的仓库
     **/
    public int compareCoordinate(String addressId){
        Address address =addressRepository.findOne(Integer.valueOf(addressId));
        Iterable<WareHouse> iwh = wareHouseRepository.findAllByCity(address.getCity());
        double lon = address.getLongitude();
        double lat = address.getLatitude();//用户纬度
        Iterator<WareHouse> whs = iwh.iterator();
        WareHouse wh = null;
        double di =10;
        while (whs.hasNext()){
            WareHouse wareHouse = whs.next();
            double lon1 =lon-wareHouse.getLongitude();
            double lat1 = lat-wareHouse.getLatitude();
            double differ = Math.sqrt(lon1*lon1+lat1*lat1);//经纬度直线距离差
            if(differ<di){
                di = differ;
                wh = wareHouse;
            }
        }
        return wh.getId();
    }

    public double[] subLocation(String coordinate){
        String[] ll= coordinate.split(",|]");
        String longitude = ll[0].substring(1);//经度
        String latitude =ll[1];//纬度
        double [] lonAndLat = new double[2];
        lonAndLat[0] = Double.valueOf(longitude);
        lonAndLat[1] = Double.valueOf(latitude);
        return lonAndLat;
    }
}
