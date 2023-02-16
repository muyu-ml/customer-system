package com.lcl.galaxy.cs.controller.graphql.datawiring;

import com.lcl.galaxy.cs.controller.graphql.datafetcher.AllCustomerStaffDataFetcher;
import com.lcl.galaxy.cs.controller.graphql.datafetcher.CustomerGroupDataFetcher;
import com.lcl.galaxy.cs.controller.graphql.datafetcher.CustomerStaffDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.boot.RuntimeWiringBuilderCustomizer;
import org.springframework.stereotype.Component;

@Component
public class CustomerStaffDataWiring implements RuntimeWiringBuilderCustomizer {
    @Autowired
    private AllCustomerStaffDataFetcher allCustomerStaffDataFetcher;
    @Autowired
    private CustomerStaffDataFetcher customerStaffDataFetcher;
    @Autowired
    private CustomerGroupDataFetcher customerGroupDataFetcher;

    @Override
    public void customize(RuntimeWiring.Builder builder){
        builder.type("Query", typeWiring -> typeWiring
                    .dataFetcher("staffs", allCustomerStaffDataFetcher)
                    .dataFetcher("staff", customerStaffDataFetcher))
                .type("CustomerStaff", typeWiring -> typeWiring
                    .dataFetcher("group", customerGroupDataFetcher));
    }
}
