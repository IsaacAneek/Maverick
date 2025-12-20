package com.example.maverick;

import java.util.ArrayList;
import java.util.List;

public class KanbanBoard {
    public List<String> TODOList;
    public List<String> OngoingList;
    public List<String> DoneList;

    public KanbanBoard() {
        TODOList = new ArrayList<String>();
        OngoingList = new ArrayList<String>();
        DoneList = new ArrayList<String>();
    }
}
