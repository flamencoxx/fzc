package com.fzc.fzcsearchneo4j.empty;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

/**
 * @author flamenco.xxx
 * @date 2021/11/30 11:13
 */
@Node("StockInfo")
@Data
@Builder
public class StockInfo {



    @Id
    private String symbol;

//    @Property(name = "name")
//    private String name;
//
//    @Property(name = "description")
//    private String description;
//
//    @Property(name = "sector")
//    private String sector;
//
//    @Property(name = "industry")
//    private String industry;

    @Property(name = "marketValue")
    private String marketValue;

    @Relationship(type = "name_in",direction = Relationship.Direction.INCOMING)
    private StockName name;

    @Relationship(type = "description_in",direction = Relationship.Direction.INCOMING)
    private StockDescription description;

    @Relationship(type = "sector_in",direction = Relationship.Direction.INCOMING)
    private StockSector sector;

    @Relationship(type = "industry_in",direction = Relationship.Direction.INCOMING)
    private StockIndustry industry;

}
