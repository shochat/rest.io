package io.model.search;

import lombok.Data;

import java.util.List;

@Data
public class Queries {
    private List<Request> request;
    private List<NextPage> nextPage;
    private List<PreviousPage> previousPage;
}
