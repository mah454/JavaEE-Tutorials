package ir.moke.javaee.api;

import com.ibm.websphere.jaxrs20.multipart.IAttachment;
import jakarta.activation.DataHandler;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.List;

@Path("/upload")
@Stateless
public class FileResources {


    /**
     * Test with curl :
     * curl -F "file1=@/path/to/filename1" -F "file2=@/path/to/filename2" -F "description=sample Description" http://localhost:8080/api/v1/upload
     *
     * Example http page :
     * <form action="http://localhost:8080/api/v1/upload" method="POST" enctype="multipart/form-data">
     *  <input type="text" name="description" />
     *  <br />
     *  <input type="file" name="file1" />
     *  <br />
     *  <input type="file" name="file2" />
     *  <br />
     *  <input type="submit" name="submit" value="submit"/>
     * </form>
     *
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public Response uploadFile(List<IAttachment> attachmentList) throws IOException {
        for (IAttachment attachment : attachmentList) {
            DataHandler dataHandler = attachment.getDataHandler();
            System.out.println("Content ID: " + attachment.getContentId());
            System.out.println("Content type: " + attachment.getContentType());
            System.out.println("File name: " + dataHandler.getName());
            System.out.println("File content type: " + dataHandler.getContentType());
            System.out.println("File length: " + dataHandler.getInputStream().readAllBytes().length);
            System.out.println("--------------------------------------");
        }
        System.out.println(" End Stream ");
        return Response.ok().build();
    }
}
