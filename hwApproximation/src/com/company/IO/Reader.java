package com.company.IO;

import com.company.Solvers.Point;
import java.io.IOException;
import java.util.ArrayList;

public interface Reader {

    boolean read(ArrayList<Point> table) throws IOException;
}
