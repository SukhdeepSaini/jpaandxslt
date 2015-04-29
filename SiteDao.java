package db.assignment6;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class SiteDao 
{

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Assignment6DB");
	private EntityManager em;
	private EntityTransaction tx;
	
	public SiteDao()
	{
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}

	public Site findSite(int siteId)
	{
		tx.begin();
		Site site = em.find(Site.class, siteId); //This will work even without using transaction as it is not persisting anything to database
		tx.commit();
		em.close();
		
		if(site != null)
		{
			return site; 
		}
		else
		{
			System.out.println("Not able to find site in database with siteID = " + siteId);
			return null;
		}
	}



	public void exportSiteDatabaseToXmlFile(SiteList siteList,String xmlFileName)
	{
		File xmlFile = new File(xmlFileName);
		try 
		{
			JAXBContext jaxb = JAXBContext.newInstance(SiteList.class);
			Marshaller marshaller = jaxb.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(siteList, xmlFile);
		} 
		catch (JAXBException ex) 
		{
			System.out.println("Please refer stack trace " + ex.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<Site> findAllSites() 
	{
		List<Site> allSites = new ArrayList<Site>();
		
		try
		{
			tx.begin();
			allSites = em.createNamedQuery("GetAllSites").getResultList();
			tx.commit();
			em.close();
		}
		catch(Exception ex)
		{
			System.out.println("An Error Occuerred while Reteriving Site Data from Database, Please refer stack trace " + ex.getStackTrace());
			return null;
		}
		
		return allSites;
	}


	public void convertXmlFileToOutputFile(String inputXmlFileName,String outputXmlFileName, String xsltFileName) 
	{
		File inputXmlFile = new File(inputXmlFileName);
		File outputXmlFile = new File(outputXmlFileName);
		File xsltFile = new File(xsltFileName);
		
		StreamSource source = new StreamSource(inputXmlFile);
		StreamSource xslt    = new StreamSource(xsltFile);
		StreamResult output = new StreamResult(outputXmlFile);
		
		TransformerFactory factory = TransformerFactory.newInstance();
		
		try
		{
			Transformer transformer = factory.newTransformer(xslt);
			transformer.transform(source, output);
		} 
		catch (TransformerConfigurationException e)
		{
			e.printStackTrace();
		} 
		catch (TransformerException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		SiteDao dao = new SiteDao();
		
		Site site = dao.findSite(1);
		
		System.out.println(site.GetName());

		List<Site> sites = dao.findAllSites();
		
		for (Site s : sites) 
		{
			System.out.println(s.GetName());
		}
		
		SiteList theSites = new SiteList();
		
		theSites.setSites(sites);
		
		dao.exportSiteDatabaseToXmlFile(theSites, "xml/sites.xml");	
		dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/sites.html", "xml/sites2html.xslt");
		dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/equipments.html", "xml/sites2equipment.xslt");
	}

}
