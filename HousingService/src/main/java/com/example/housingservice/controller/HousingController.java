package com.example.housingservice.controller;


import com.example.housingservice.domain.HouseResponse;
import com.example.housingservice.entity.*;
import com.example.housingservice.service.*;
import com.example.housingservice.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class HousingController {
    private FacilityService facilityService;
    private HouseService houseService;
    private LandlordService landlordService;
    private FacilityReportService facilityReportService;
    private FacilityReportDetailService facilityReportDetailService;

    @Autowired
    public void setFacilityService (FacilityService facilityService){
        this.facilityService = facilityService;
    }
    @Autowired
    public void setHouseService (HouseService houseService){
        this.houseService = houseService;
    }
    @Autowired
    public void setLandlordService (LandlordService landlordService){
        this.landlordService = landlordService;
    }
    @Autowired
    public void setFacilityReportService (FacilityReportService facilityReportService){
        this.facilityReportService = facilityReportService;
    }
    @Autowired
    public void setFacilityReportDetailService (FacilityReportDetailService facilityReportDetailService){
        this.facilityReportDetailService = facilityReportDetailService;
    }

    @GetMapping
    public String generateDate(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        //Created 2 landlord
        Landlord l1 = Landlord.builder()
                .FirstName("aaaaa")
                .LastName("aaaaa")
                .Email("aaaaa")
                .CellPhone("aaaaa")
                .build();
        Landlord l2 = Landlord.builder()
                .FirstName("bbbbb")
                .LastName("bbbbb")
                .Email("bbbbb")
                .CellPhone("bbbbb")
                .build();

        //Created 5 house - 3 for landlord 1, 2 for landlord 2
        House h1l1 = House.builder().Address("h1l1").MaxOccupant(5).landlord(l1).build();
        House h2l1 = House.builder().Address("h2l1").MaxOccupant(5).landlord(l1).build();
        House h3l1 = House.builder().Address("h3l1").MaxOccupant(5).landlord(l1).build();

        House h1l2 = House.builder().Address("h1l2").MaxOccupant(5).landlord(l2).build();
        House h2l2 = House.builder().Address("h2l2").MaxOccupant(5).landlord(l2).build();

        //h1l1 facility
        Facility h1l1f1 = Facility.builder().Type("Bed").Description("Metal Frame").Quantity("4").house(h1l1).build();
        Facility h1l1f2 = Facility.builder().Type("Mattress").Description("Single").Quantity("6").house(h1l1).build();
        Facility h1l1f3 = Facility.builder().Type("Table").Description("Office Table").Quantity("4").house(h1l1).build();
        Facility h1l1f4 = Facility.builder().Type("Chair").Description("Office Chair").Quantity("8").house(h1l1).build();

        //h2l1 facility - unfurnished
        Facility h2l1f1 = Facility.builder().Type("Bed").Description("Metal Frame").Quantity("0").house(h2l1).build();
        Facility h2l1f2 = Facility.builder().Type("Mattress").Description("Single").Quantity("0").house(h2l1).build();
        Facility h2l1f3 = Facility.builder().Type("Table").Description("Office Table").Quantity("0").house(h2l1).build();
        Facility h2l1f4 = Facility.builder().Type("Chair").Description("Office Chair").Quantity("0").house(h2l1).build();

        //h3l1 facility
        Facility h3l1f1 = Facility.builder().Type("Bed").Description("Metal Frame").Quantity("5").house(h3l1).build();
        Facility h3l1f2 = Facility.builder().Type("Mattress").Description("Single").Quantity("5").house(h3l1).build();
        Facility h3l1f3 = Facility.builder().Type("Table").Description("Office Table").Quantity("5").house(h3l1).build();
        Facility h3l1f4 = Facility.builder().Type("Chair").Description("Office Chair").Quantity("5").house(h3l1).build();

        //h1l2 facility
        Facility h1l2f1 = Facility.builder().Type("Bed").Description("Wood Frame").Quantity("3").house(h1l2).build();
        Facility h1l2f2 = Facility.builder().Type("Mattress").Description("Single").Quantity("3").house(h1l2).build();
        Facility h1l2f3 = Facility.builder().Type("Table").Description("Office Table").Quantity("3").house(h1l2).build();
        Facility h1l2f4 = Facility.builder().Type("Chair").Description("Office Chair").Quantity("3").house(h1l2).build();

        //h2l2 facility
        Facility h2l2f1 = Facility.builder().Type("Bed").Description("Metal Frame").Quantity("4").house(h2l2).build();
        Facility h2l2f2 = Facility.builder().Type("Mattress").Description("Single").Quantity("4").house(h2l2).build();
        Facility h2l2f3 = Facility.builder().Type("Table").Description("Office Table").Quantity("4").house(h2l2).build();
        Facility h2l2f4 = Facility.builder().Type("Chair").Description("Office Chair").Quantity("4").house(h2l2).build();

        //only 2 reports
        FacilityReport report1 = FacilityReport.builder().facility(h2l1f4).EmployeeID("h2b3h2bj3b2b").Title("LackingChair").Description("There is no chair in the house").Status("Close").build();
        FacilityReportDetail frdReport1 = FacilityReportDetail.builder().EmployeeID("h2b3h2bj3b2b").facilityReport(report1).Comment("Each bring 1 chair").CreateDate(new Date(System.currentTimeMillis())).LastModificationDate(new Date(System.currentTimeMillis())).build();
        FacilityReportDetail frdReport2 = FacilityReportDetail.builder().EmployeeID("aaaaaaaaaaaa").facilityReport(report1).Comment("OK").CreateDate(new Date(System.currentTimeMillis())).LastModificationDate(new Date(System.currentTimeMillis())).build();
        FacilityReportDetail frdReport3 = FacilityReportDetail.builder().EmployeeID("bbbbbbbbbbbb").facilityReport(report1).Comment("OK").CreateDate(new Date(System.currentTimeMillis())).LastModificationDate(new Date(System.currentTimeMillis())).build();
        FacilityReportDetail frdReport4 = FacilityReportDetail.builder().EmployeeID("testtesttest").facilityReport(report1).Comment("OK").CreateDate(new Date(System.currentTimeMillis())).LastModificationDate(new Date(System.currentTimeMillis())).build();
        FacilityReportDetail frdReport5 = FacilityReportDetail.builder().EmployeeID("muahahahhaha").facilityReport(report1).Comment("OK").CreateDate(new Date(System.currentTimeMillis())).LastModificationDate(new Date(System.currentTimeMillis())).build();

        session.persist(l1);
        session.persist(l2);
        session.persist(h1l1);
        session.persist(h2l1);
        session.persist(h3l1);
        session.persist(h1l2);
        session.persist(h2l2);
        session.persist(h1l1f1);
        session.persist(h1l1f2);
        session.persist(h1l1f3);
        session.persist(h1l1f4);
        session.persist(h2l1f1);
        session.persist(h2l1f2);
        session.persist(h2l1f3);
        session.persist(h2l1f4);
        session.persist(h3l1f1);
        session.persist(h3l1f2);
        session.persist(h3l1f3);
        session.persist(h3l1f4);
        session.persist(h1l2f1);
        session.persist(h1l2f2);
        session.persist(h1l2f3);
        session.persist(h1l2f4);
        session.persist(h2l2f1);
        session.persist(h2l2f2);
        session.persist(h2l2f3);
        session.persist(h2l2f4);
        session.persist(report1);
        session.persist(frdReport1);
        session.persist(frdReport2);
        session.persist(frdReport3);
        session.persist(frdReport4);
        session.persist(frdReport5);
        tx.commit();
        return "Data generated";
    }

    @GetMapping("/houses")
    public List<HouseResponse> viewAllHouses(){
        return houseService.getAllHouses();
    }

    @GetMapping("/{address}")
    public HouseResponse viewHouseDetail(){

    }

}
