package com.workstation.amrith.todo;

import com.orm.SugarRecord;

/**
 * Created by amrith on 2/27/18.
 */

public class Entry extends SugarRecord<Entry>
{
    public String content;
    public String date;

    public Entry()
    {

    }

    public Entry(String a, String b)
    {
        content = a;
        date = b;
    }
}
