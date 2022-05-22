package main;

import tech.tablesaw.api.ColumnType;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.HorizontalBarPlot;
import tech.tablesaw.plotly.api.PiePlot;
import tech.tablesaw.plotly.api.TimeSeriesPlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.traces.BarTrace;
import tech.tablesaw.selection.Selection;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

import static tech.tablesaw.aggregate.AggregateFunctions.mean;
import static tech.tablesaw.aggregate.AggregateFunctions.sum;
import static tech.tablesaw.api.ColumnType.*;

public class DiversityDashboard {
    public static void main(String[] args) {
        //Title,Genres,Rank,Popularity,Score,Episodes,Episode length,Release Date
        ColumnType[] types = {STRING, STRING , STRING, FLOAT, FLOAT , STRING, STRING, STRING};

        File tempfile = new File("data/MALratings.csv");

        if (!tempfile.exists())
        {
            System.out.println("file not found");
            return;
        }

        System.out.println("File found");

        Table t = Table.read().usingOptions(CsvReadOptions
                .builder("data/MALratings.csv")
                .columnTypes(types));


        // Sum the number of fatalities from each tornado, grouping by scale
        // Plot
        Plot.show(
                TimeSeriesPlot.create(
                        "Anime Rating ", // plot title
                        t, // table
                        "Title", // grouping column name
                        "Score")); // numeric column name

//        // Plot the mean injuries rather than a sum.
//        Table injuries1 = t.summarize("injuries", mean).by("Diversity-Index");
//
//        Plot.show(
//                HorizontalBarPlot.create(
//                        "Average number of tornado injuries by scale", injuries1, "scale", "mean [injuries]"));
//
//        // PIE PLOT
//        Plot.show(PiePlot.create("fatalities by scale", fatalities1, "scale", "sum [fatalities]"));
//
//        // PARETO PLOT
//        Table t2 = t.summarize("fatalities", sum).by("Diversity-Index");
//
//        t2 = t2.sortDescendingOn(t2.column(1).name());
//        Layout layout = Layout.builder().title("Tornado Fatalities by State").build();
//        BarTrace trace = BarTrace.builder(t2.categoricalColumn(0), t2.numberColumn(1)).build();
//        Plot.show(new Figure(layout, trace));
    }
}