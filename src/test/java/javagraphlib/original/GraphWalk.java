package javagraphlib.original;

import javagraphlib.com.onelake.api.error.OnelakeException;
import javagraphlib.original.notgiven.*;

import java.util.*;

public class GraphWalk {

    private final Workflow workflow;
    private final Job job;
    private final Application application;
    private final ComponentRepository componentRepository;

    public GraphWalk(Workflow workflow,
                     Job job,
                     Application application,
                     ComponentRepository componentRepository)
    {
        this.workflow = workflow;
        this.job = job;
        this.application = application;
        this.componentRepository = componentRepository;
    }

    public void execute() throws OnelakeException {
        GraphBuilder graphBuilder = new GraphBuilder(this.workflow);
        DirectedGraph<Node> graph = graphBuilder.getGraph();
        Map<String, Node> nodeMap = graphBuilder.getNodeMap();
        Node start = graphBuilder.getStartNode();

        Collection<Node> visited = new HashSet<>();
        visited.add(start);

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(start);

        // Run start node
        Component component = getComponent(start);
        component.getInPorts();

        //ExecutionResult output = callable.run(start);
        System.out.println(component.getClass().getName());

        // Run other nodes from start node
        while (!queue.isEmpty()) {
            Node current = queue.remove();
            Node node= null;
            Iterator<Node> iterator = graph.getNeighborsFor(current).iterator();

            while (iterator.hasNext()) {
                node = iterator.next();
                if (!visited.contains(node)) {
                    visited.add(node);
                    queue.add(node);
                    component = getComponent(node);
                    //ExecutionResult output = callable.run(node);
                    System.out.println(component.getClass().getName());
                }
            }
        }
    }

    private Component getComponent(Node node) throws OnelakeException {
        ComponentInfo componentInfo = node.getComponentInfo();
        String actionClass = (String) componentRepository.getComponents().get(componentInfo.getId()+"-"+componentInfo.getName()+"-"+componentInfo.getVersion());
        ClassBuilder<Component> classReader = new ClassBuilder<Component>();
        return  classReader.buildInstance(actionClass);
    }
}