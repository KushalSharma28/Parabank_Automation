import os
import json
from datetime import datetime

class TestReportGenerator:
    """Generate custom test reports"""
    
    def __init__(self, report_dir="reports"):
        self.report_dir = report_dir
        if not os.path.exists(self.report_dir):
            os.makedirs(self.report_dir)
    
    def generate_json_report(self, test_results):
        """Generate JSON format report"""
        report_file = os.path.join(
            self.report_dir, 
            f"test_report_{datetime.now().strftime('%Y%m%d_%H%M%S')}.json"
        )
        with open(report_file, 'w') as f:
            json.dump(test_results, f, indent=4)
        return report_file
    
    def generate_summary_report(self, total_tests, passed, failed, skipped):
        """Generate summary report"""
        report_file = os.path.join(
            self.report_dir, 
            f"summary_{datetime.now().strftime('%Y%m%d_%H%M%S')}.txt"
        )
        with open(report_file, 'w') as f:
            f.write("=" * 50 + "\n")
            f.write("Test Execution Summary\n")
            f.write("=" * 50 + "\n")
            f.write(f"Total Tests: {total_tests}\n")
            f.write(f"Passed: {passed}\n")
            f.write(f"Failed: {failed}\n")
            f.write(f"Skipped: {skipped}\n")
            f.write(f"Success Rate: {(passed/total_tests*100):.2f}%\n")
            f.write(f"Execution Time: {datetime.now()}\n")
            f.write("=" * 50 + "\n")
        return report_file
