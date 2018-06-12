//package com.newx.compiler.processor;
//
//import com.newx.compiler.utils.Logger;
//import com.newx.compiler.utils.TypeUtils;
//import com.newx.com.newx.base.frameworks.route.facade.annotation.NotRepeatAble;
//import com.newx.com.newx.base.frameworks.route.facade.annotation.Route;
//import com.google.auto.service.AutoService;
//
//import org.apache.commons.collections4.CollectionUtils;
//
//import java.io.IOException;
//import java.util.Set;
//
//import javax.annotation.processing.AbstractProcessor;
//import javax.annotation.processing.ProcessingEnvironment;
//import javax.annotation.processing.Processor;
//import javax.annotation.processing.RoundEnvironment;
//import javax.annotation.processing.SupportedAnnotationTypes;
//import javax.annotation.processing.SupportedOptions;
//import javax.annotation.processing.SupportedSourceVersion;
//import javax.lang.model.SourceVersion;
//import javax.lang.model.element.Element;
//import javax.lang.model.element.TypeElement;
//
//import static com.newx.compiler.utils.Consts.ANNOTATION_TYPE_AUTOWIRED;
//import static com.newx.compiler.utils.Consts.ANNOTATION_TYPE_ROUTE;
//import static com.newx.compiler.utils.Consts.KEY_MODULE_NAME;
//
///**
// * Created by xuzhijian on 2018/4/13 0013.
// */
//
//@AutoService(Processor.class)
//@SupportedOptions(KEY_MODULE_NAME)
//@SupportedSourceVersion(SourceVersion.RELEASE_7)
//@SupportedAnnotationTypes({ANNOTATION_TYPE_ROUTE, ANNOTATION_TYPE_AUTOWIRED})
//public class CheckProcessor  extends AbstractProcessor {
//
//    private Logger logger;
//
//    @Override
//    public synchronized void init(ProcessingEnvironment processingEnv) {
//        super.init(processingEnv);
//        logger = new Logger(processingEnv.getMessager());   // Package the log utils.
//    }
//
//    @Override
//    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//        if (CollectionUtils.isNotEmpty(annotations)) {
//            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(NotRepeatAble.class);
//            try {
//                logger.info(">>> Found NotRepeatAble, start... <<<");
//                parseChecks(elements);
//            } catch (Exception e) {
//                logger.error(e);
//            }
//            return true;
//        }
//
//        return false;
//    }
//
//    private void parseChecks(Set<? extends Element> routeElements) throws IOException {
//
//    }
//}
